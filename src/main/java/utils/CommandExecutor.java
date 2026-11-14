package com.kubepulse.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class CommandExecutor {

    // Naya method jo live output ko callback (UI log function) ke zariye bhejega
    public static void runCommand(String command, Consumer<String> onOutputLine) {
        try {
            ProcessBuilder processBuilder;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            } else {
                processBuilder = new ProcessBuilder("bash", "-c", command);
            }

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[CLI] " + line);
                if (onOutputLine != null) {
                    onOutputLine.accept(line); // Yeh UI par live log bhejega
                }
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            if (onOutputLine != null) {
                onOutputLine.accept("[ERROR] " + e.getMessage());
            }
        }
    }

    // Purana method taaki baaki jagah error na aaye
    public static void runCommand(String command) {
        runCommand(command, null);
    }
}