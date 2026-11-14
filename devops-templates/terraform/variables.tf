variable "aws_region" {
  description = "The AWS region to provision resources in"
  type        = string
  default     = "us-east-1"
}

variable "instance_type" {
  description = "EC2 instance type for DevOps server"
  type        = string
  default     = "t2.medium"
}

variable "server_name" {
  description = "Name tag for the server"
  type        = string
  default     = "KubePulse-DevOps-Server"
}