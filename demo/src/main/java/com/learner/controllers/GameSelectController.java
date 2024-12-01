package com.learner.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameSelectController implements Initializable {

    @FXML
    private VBox mainVbox;

    @FXML
    private Label title; // Language + difficulty + "games"

    private HashMap<String, ScrollPane> categoryScrollPanes; // ScrollPane (linked to category)

    private HashMap<String, HBox> categoryHBoxes; // Hbox (linked to category)

    private HashMap<String, ArrayList<VBox>> gameBoxes; // ArrayList of VBoxes holding title and button w/ image (linked to category)

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if (mainVbox == null || title == null) {
        //     System.err.println("FXML elements not injected properly.");
        //     return;
        // }

        for (int i = 0; i < 30; i++) {
            // Create a new button for each game
            Button gameButton = new Button("Game " + (i + 1));
    
            // Assign a unique UUID to the button using setUserData
            UUID gameUUID = UUID.randomUUID();
            gameButton.setUserData(gameUUID);
    
            // Add the button to your VBox or other container
            mainVbox.getChildren().add(gameButton);
    
            // Set up the event handler
            gameButton.setOnAction(event -> {
                // Retrieve the UUID from the button
                UUID clickedUUID = (UUID) gameButton.getUserData();
                System.out.println("Button clicked! UUID: " + clickedUUID);
            });
        }
        String selectedLanguage = "English";
        String difficulty = "Easy";
        title.setText(difficulty + " " + selectedLanguage + " Games");
    }
}
