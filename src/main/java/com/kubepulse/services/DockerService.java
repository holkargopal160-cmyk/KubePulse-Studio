package com.kubepulse.services;

import com.kubepulse.utils.CommandExecutor;

public class DockerService {

    public static void buildDockerImage(String imageName, String dockerfilePath) {
        System.out.println("[DOCKER] Building Docker image: " + imageName + "...");
        new Thread(() -> {
            String command = "docker build -t " + imageName + " -f " + dockerfilePath + " .";
            CommandExecutor.runCommand(command);
            System.out.println("[DOCKER] Image build process completed.");
        }).start();
    }

    public static void runContainer(String imageName, String containerName, int hostPort, int containerPort) {
        System.out.println("[DOCKER] Running container " + containerName + " on port " + hostPort + "...");
        new Thread(() -> {
            String command = "docker run -d --name " + containerName + " -p " + hostPort + ":" + containerPort + " " + imageName;
            CommandExecutor.runCommand(command);
        }).start();
    }
}