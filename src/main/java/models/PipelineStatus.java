package com.kubepulse.models;

public class PipelineStatus {
    private String stageName; // e.g., "Docker Build", "Terraform Apply"
    private String status;    // "SUCCESS", "FAILED", "RUNNING", "PENDING"
    private String message;

    public PipelineStatus(String stageName, String status, String message) {
        this.stageName = stageName;
        this.status = status;
        this.message = message;
    }

    // Getters and Setters
    public String getStageName() { return stageName; }
    public void setStageName(String stageName) { this.stageName = stageName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}