package com.kubepulse.services;

import com.kubepulse.utils.CommandExecutor;

public class K8sService {

    public static void deployToCluster(String manifestPath) {
        System.out.println("[K8S] Applying Kubernetes deployment manifests...");
        CommandExecutor.runCommand("kubectl apply -f " + manifestPath);
    }

    public static void rollbackDeployment(String deploymentName) {
        System.out.println("[K8S] Rolling back deployment: " + deploymentName);
        CommandExecutor.runCommand("kubectl rollout undo deployment/" + deploymentName);
    }

    public static void getClusterPods() {
        System.out.println("[K8S] Fetching active pods status...");
        CommandExecutor.runCommand("kubectl get pods -o wide");
    }
}