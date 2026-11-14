package com.kubepulse.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JenkinsService {

    private static final String JENKINS_URL = "http://localhost:8080";
    private static final String JOB_NAME = "KubePulse-Pipeline";
    private static final String USER = "admin";
    private static final String API_TOKEN = "your_jenkins_api_token_here";

    public static void triggerBuild() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String auth = USER + ":" + API_TOKEN;
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(JENKINS_URL + "/job/" + JOB_NAME + "/build"))
                    .header("Authorization", "Basic " + encodedAuth)
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 201 || response.statusCode() == 200) {
                System.out.println("[JENKINS] Build triggered successfully!");
            } else {
                System.out.println("[JENKINS] Failed to trigger build. Status Code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}