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
  region = "us-east-2" # <-- CAMBIA si usas otra región
}

############################################
# Security Group para la instancia EC2     #
############################################
resource "aws_security_group" "franquicia_sg" {
  name        = "franquicia-sg"
  description = "Permite HTTP (8080) y SSH"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["186.29.114.232/32"]
  }

  # API franquicias (puerto 8080)
  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]       # acceso público a tu API
  }

  # Salida libre a internet (para apt-get, git, etc.)
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

############################################
# Script de arranque (user_data)           #
# - Instala Docker + docker-compose        #
# - Clona tu repo y levanta la app         #
############################################
locals {
  user_data = <<-EOF
    #!/bin/bash
    set -e

    # Actualizar paquetes
    apt-get update -y

    # Instalar Docker y Git
    apt-get install -y docker.io git

    # Habilitar y arrancar Docker
    systemctl enable docker
    systemctl start docker

    # Instalar plugin docker-compose (Docker CLI v2)
    mkdir -p ~/.docker/cli-plugins/
    curl -SL https://github.com/docker/compose/releases/download/v2.29.7/docker-compose-linux-x86_64 -o ~/.docker/cli-plugins/docker-compose
    chmod +x ~/.docker/cli-plugins/docker-compose

    # Crear carpeta para la app
    mkdir -p /opt/franquicias
    cd /opt

    # Clonar tu repositorio
    git clone https://github.com/TU_USUARIO/TU_REPO_Franquicias.git franquicias  # <-- CAMBIA ESTO
    cd franquicias

    # Levantar la app con docker compose
    docker compose up -d
  EOF
}

############################################
# Instancia EC2                            #
############################################
resource "aws_instance" "franquicia_ec2" {
  ami           = "ami-0f5fcdfbd140e4ab7"  # <-- CAMBIA por una AMI de Ubuntu en tu región
  instance_type = "t3.micro"            # dentro del free tier si usas t2.micro/t3.micro

  vpc_security_group_ids = [aws_security_group.franquicia_sg.id]

  # key pair para poder conectarte por SSH (debe existir ya en AWS)
  key_name = "franquicia-key"     # <-- CAMBIA (ej: juandiego-key)

  user_data = local.user_data

  tags = {
    Name = "franquicia-app"
  }
}

############################################
# Outputs útiles                            #
############################################
output "ec2_public_ip" {
  description = "IP pública de la instancia EC2 donde corre la app"
  value       = aws_instance.franquicia_ec2.public_ip
}

output "api_url" {
  description = "URL base de la API"
  value       = "http://${aws_instance.franquicia_ec2.public_ip}:8080"
}
