package com.kubepulse.server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.kubepulse.utils.CommandExecutor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class WebhookListener {
    private static HttpServer server;

    public static void startServer(int port) {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/webhook", new WebhookHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("[INFO] Webhook Listener started on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class WebhookHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                System.out.println("[WEBHOOK] GitHub Push Event Received! Triggering Zero-Click Pipeline...");
                
                // Background thread mein pipeline commands run karein
                new Thread(() -> {
                    CommandExecutor.runCommand("echo [Pipeline] Pulling latest code & building...");
                    // Yahan aap Jenkins API trigger ya Docker build script run kar sakte hain
                }).start();

                String response = "Pipeline Triggered Successfully!";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }
}