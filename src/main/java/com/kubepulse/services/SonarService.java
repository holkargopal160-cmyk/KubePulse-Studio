package com.kubepulse.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SonarService {

    private static final String SONAR_URL = "http://localhost:9000";
    private static final String PROJECT_KEY = "kubepulse-app";

    public static void checkQualityGate() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SONAR_URL + "/api/qualitygates/project_status?projectKey=" + PROJECT_KEY))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("[SONARQUBE] Quality Gate Response: " + response.body());
            // Yahan JSON parse karke check kar sakte hain ki status 'OK' hai ya 'ERROR'
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}