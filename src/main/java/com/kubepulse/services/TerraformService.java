package com.kubepulse.services;

import com.kubepulse.utils.CommandExecutor;

public class TerraformService {

    private static final String TERRAFORM_DIR = "devops-templates/terraform/";

    public static void applyTerraform() {
        System.out.println("[TERRAFORM] Initializing and applying infrastructure...");
        new Thread(() -> {
            CommandExecutor.runCommand("cd " + TERRAFORM_DIR + " && terraform init && terraform apply -auto-approve");
        }).start();
    }

    public static void destroyTerraform() {
        System.out.println("[TERRAFORM] Destroying cloud resources...");
        new Thread(() -> {
            CommandExecutor.runCommand("cd " + TERRAFORM_DIR + " && terraform destroy -auto-approve");
        }).start();
    }
}