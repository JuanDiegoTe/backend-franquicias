############################################
# Configuración básica de Terraform + AWS  #
############################################
terraform {
  required_version = ">= 1.5.0"

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = "REGION"
}

resource "aws_security_group" "franquicia_sg" {
  name        = "franquicia-sg"
  description = "Permite HTTP (8080) y SSH"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["186.29.114.232/32"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]       # acceso público a tu API
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

locals {
  user_data = <<-EOF
    #!/bin/bash
    set -e
    apt-get update -y
    apt-get install -y docker.io git
    
    systemctl enable docker
    systemctl start docker

    mkdir -p ~/.docker/cli-plugins/
    curl -SL https://github.com/docker/compose/releases/download/v2.29.7/docker-compose-linux-x86_64 -o ~/.docker/cli-plugins/docker-compose
    chmod +x ~/.docker/cli-plugins/docker-compose

    mkdir -p /opt/franquicias
    cd /opt

    git clone https://github.com/TU_USUARIO/TU_REPO_Franquicias.git franquicias  # <-- CAMBIA ESTO
    cd franquicias
    docker compose up -d
  EOF
}


resource "aws_instance" "franquicia_ec2" {
  ami           = "AMI_UPDATE" 
  instance_type = "t3.micro"            

  vpc_security_group_ids = [aws_security_group.franquicia_sg.id]

  key_name = "PAIR_KEY"  

  user_data = local.user_data

  tags = {
    Name = "franquicia-app"
  }
}

output "ec2_public_ip" {
  description = "IP pública de la instancia EC2 donde corre la app"
  value       = aws_instance.franquicia_ec2.public_ip
}

output "api_url" {
  description = "URL base de la API"
  value       = "http://${aws_instance.franquicia_ec2.public_ip}:8080"
}
