package com.kubepulse.models;

public class ProjectConfig {
    private String projectName;
    private String repoUrl;
    private String branch;
    private String dockerImageName;

    public ProjectConfig(String projectName, String repoUrl, String branch, String dockerImageName) {
        this.projectName = projectName;
        this.repoUrl = repoUrl;
        this.branch = branch;
        this.dockerImageName = dockerImageName;
    }

    // Getters and Setters
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getRepoUrl() { return repoUrl; }
    public void setRepoUrl(String repoUrl) { this.repoUrl = repoUrl; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public String getDockerImageName() { return dockerImageName; }
    public void setDockerImageName(String dockerImageName) { this.dockerImageName = dockerImageName; }
}