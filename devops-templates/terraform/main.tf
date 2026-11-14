terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.0"
    }
  }
}

provider "aws" {
  region = "us-east-1"
}

resource "aws_instance "kubepulse_node" {
  ami           = "ami-0c7217cdde317cfec" # Ubuntu 22.04 LTS
  instance_type = "t2.medium"

  tags = {
    Name = "KubePulse-DevOps-Server"
  }
}

output "server_ip" {
  value = aws_instance.kubepulse_node.public_ip
}