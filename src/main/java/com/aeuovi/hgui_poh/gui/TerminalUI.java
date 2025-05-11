package com.aeuovi.hgui_poh.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TerminalUI extends Application {

    private TextArea outputArea;
    private TextField inputField;

    @Override
    public void start(Stage primaryStage) {
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setStyle("-fx-control-inner-background:black; -fx-text-fill:lime; -fx-font-family:'Consolas'; -fx-font-size:14;");

        inputField = new TextField();
        inputField.setStyle("-fx-control-inner-background:black; -fx-text-fill:lime; -fx-font-family:'Consolas'; -fx-font-size:14;");
        inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String command = inputField.getText().trim();
                processCommand(command);
                inputField.clear();
            }
        });

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setCenter(outputArea);
        root.setBottom(inputField);

        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setTitle("Passport Office Helper Terminal");
        primaryStage.setScene(scene);
        primaryStage.show();

        log("Welcome to Passport Office Helper v2.0");
        log("Type 'help' to see available commands.");
    }

    private void processCommand(String command) {
        log("> " + command);
        switch (command.toLowerCase()) {
            case "help":
                log("Available commands: help, exit, status");
                break;
            case "exit":
                log("Goodbye!");
                System.exit(0);
                break;
            case "status":
                log("All systems nominal. EarthMC API reachable.");
                break;
            default:
                log("Unknown command. Type 'help' for a list of commands.");
                break;
        }
    }

    private void log(String message) {
        outputArea.appendText(message + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

