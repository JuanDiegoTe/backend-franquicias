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
  region = "us-east-2" # Cambia si usas otra región
}

############################################
# Security Group para la instancia EC2     #
############################################
resource "aws_security_group" "franquicia_sg" {
  name        = "franquicia-sg"
  description = "Permite HTTP (8080) y SSH"

  # SSH solo desde tu IP
  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["186.29.114.232/32"]
  }

  # API franquicias (puerto 8080) abierta
  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # Salida libre a internet
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

############################################
# Script de arranque (user_data)           #
# - Instala Docker + git                   #
# - Clona tu repo y levanta la app         #
############################################
############################################
# Script de arranque (user_data)           #
# - Instala Docker + docker-compose-v2     #
# - Agrega ubuntu al grupo docker          #
# - Clona el repo y levanta la app         #
############################################
locals {
  user_data = <<-EOF
    #!/bin/bash
    exec > /var/log/user-data.log 2>&1
    echo "=== INICIO USER-DATA ==="

    export DEBIAN_FRONTEND=noninteractive

    echo "[1] apt-get update"
    apt-get update -y

    echo "[2] instalar docker.io, docker-compose-v2 y git"
    apt-get install -y docker.io docker-compose-v2 git

    echo "[3] habilitar y arrancar docker"
    systemctl enable docker
    systemctl start docker
    sleep 5

    echo "[4] agregar usuario ubuntu al grupo docker"
    usermod -aG docker ubuntu

    echo "[5] crear carpeta /opt/franquicias"
    mkdir -p /opt/franquicias
    cd /opt

    echo "[6] clonar repo (si no existe)"
    if [ ! -d "/opt/franquicias/.git" ]; then
      git clone https://github.com/JuanDiegoTe/backend-franquicias.git franquicias
    fi

    cd /opt/franquicias

    echo "[7] build + up de docker compose"
    docker compose build || { echo "docker compose build falló. Revisar /var/log/user-data.log"; exit 1; }
    docker compose up -d || { echo "docker compose up falló. Revisar /var/log/user-data.log"; exit 1; }

    echo "[8] dejar /opt/franquicias propiedad de ubuntu"
    chown -R ubuntu:ubuntu /opt/franquicias || true

    usermod -aG docker ubuntu
    echo "=== FIN USER-DATA ==="
  EOF
}

############################################
# Instancia EC2                            #
############################################
resource "aws_instance" "franquicia_ec2" {
  # ⚠️ Sustituye por el AMI ID que copiaste de la consola
  ami           = "ami-0f5fcdfbd140e4ab7"
  instance_type = "t3.micro" # free tier compatible

  vpc_security_group_ids = [aws_security_group.franquicia_sg.id]

  # key pair para poder conectarte por SSH (debe existir ya en AWS)
  key_name = "franquicia-key" # cámbialo por el nombre real de tu key pair

  user_data = local.user_data

  tags = {
    Name = "franquicia-app"
  }
}

############################################
# Outputs útiles                           #
############################################
output "ec2_public_ip" {
  description = "IP pública de la instancia EC2 donde corre la app"
  value       = aws_instance.franquicia_ec2.public_ip
}

output "api_url" {
  description = "URL base de la API"
  value       = "http://${aws_instance.franquicia_ec2.public_ip}:8080"
}
