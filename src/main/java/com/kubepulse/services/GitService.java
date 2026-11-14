package com.kubepulse.services;

import com.kubepulse.utils.CommandExecutor;
import java.io.File;

public class GitService {

    public static void cloneOrPullRepo(String repoUrl, String destinationDir) {
        File dir = new File(destinationDir);
        if (dir.exists() && new File(destinationDir + "/.git").exists()) {
            System.out.println("[GIT] Repository already exists. Pulling latest changes...");
            CommandExecutor.runCommand("cd " + destinationDir + " && git pull origin main");
        } else {
            System.out.println("[GIT] Cloning repository from " + repoUrl + "...");
            CommandExecutor.runCommand("git clone " + repoUrl + " " + destinationDir);
        }
    }
}