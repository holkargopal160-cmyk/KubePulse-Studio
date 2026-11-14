package com.kubepulse.services;

import com.kubepulse.utils.CommandExecutor;

public class AnsibleService {

    public static void runSetupPlaybook() {
        System.out.println("[ANSIBLE] Starting server configuration via Ansible playbook...");
        
        // CommandExecutor ka use karke Ansible playbook run karein
        String playbookPath = "devops-templates/ansible/setup-playbook.yml";
        String inventoryPath = "devops-templates/ansible/inventory.ini";
        
        String command = "ansible-playbook -i " + inventoryPath + " " + playbookPath;
        
        new Thread(() -> {
            CommandExecutor.runCommand(command);
            System.out.println("[ANSIBLE] Playbook execution completed.");
        }).start();
    }
}