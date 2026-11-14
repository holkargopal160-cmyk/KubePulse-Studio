package com.kubepulse.bridge;

import com.kubepulse.utils.CommandExecutor;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;

public class JSBridgeController {

    private WebEngine webEngine;

    // WebEngine set karne ke liye setter method
    public void setWebEngine(WebEngine webEngine) {
        this.webEngine = webEngine;
    }

    public void triggerDeployment(String repoUrl) {
        appendLog("Triggering deployment pipeline for repository: " + repoUrl);
        
        // Background thread mein pipeline commands run karein taaki UI freeze na ho
        new Thread(() -> {
            try {
                appendLog("[INFO] Starting pipeline execution...");
                
                // Naya callback wala method use karein taaki live output UI par aaye
                CommandExecutor.runCommand("echo Starting Pipeline for " + repoUrl, logLine -> {
                    appendLog("[CLI] " + logLine);
                });
                
                // Yahan aap apne baaki commands (Docker, Ansible, K8s) run kar sakte hain
                appendLog("[SUCCESS] Pipeline completed successfully for: " + repoUrl);
            } catch (Exception e) {
                appendLog("[ERROR] Failed: " + e.getMessage());
            }
        }).start();
    }

    public void runTerraformApply() {
        appendLog("Running Terraform Infrastructure Provisioning...");
        new Thread(() -> {
            CommandExecutor.runCommand("terraform --version", logLine -> {
                appendLog("[CLI] " + logLine);
            });
            appendLog("[SUCCESS] Terraform version checked.");
        }).start();
    }

    public void runK8sRollback() {
        appendLog("Executing Kubernetes Rollback...");
        new Thread(() -> {
            CommandExecutor.runCommand("kubectl rollout undo deployment/my-app-deployment", logLine -> {
                appendLog("[CLI] " + logLine);
            });
            appendLog("[SUCCESS] Rollback command executed.");
        }).start();
    }

    // Yeh method backend ke logs ko UI (HTML/JS) par live bhejega
    private void appendLog(String message) {
        System.out.println(message); // Console ke liye
        if (webEngine != null) {
            Platform.runLater(() -> {
                // HTML ke JavaScript function ko call karega
                webEngine.executeScript("appendTerminalLog(\"" + message.replace("\"", "\\\"") + "\");");
            });
        }
    }
}