package com.kubepulse;

import java.net.URL;

import com.kubepulse.bridge.JSBridgeController;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("KubePulse Studio - DevOps Control Center");

        BorderPane root = new BorderPane();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // 1. Controller ka object yahan banayein aur WebEngine pass karein
        JSBridgeController bridgeController = new JSBridgeController();
        bridgeController.setWebEngine(webEngine);

        // Load index.html from resources
        URL url = getClass().getResource("/web/index.html");
        if (url != null) {
            webEngine.load(url.toExternalForm());
        } else {
            System.err.println("Could not find /web/index.html");
        }

        // Bridge setup when page finishes loading
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                JSObject win = (JSObject) webEngine.executeScript("window");
                // 2. Naye object ki jagah wahi bridgeController window par bind karein
                win.setMember("javaBridge", bridgeController);
            }
        });

        root.setCenter(webView);
        Scene scene = new Scene(root, 1280, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}