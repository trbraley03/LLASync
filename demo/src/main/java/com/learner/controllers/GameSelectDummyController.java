package com.learner.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameSelectDummyController {

    @FXML
    private VBox mainVBox;  // Outer VBox containing ScrollPanes for each category

    // Method to populate the categories dynamically
    public void initialize() {
        // List of categories
        ObservableList<String> categories = FXCollections.observableArrayList(
            "Action", "Adventure", "Puzzle", "RPG"
        );
    
        // Iterate through the categories and create UI components
        for (String category : categories) {
            // Create a ScrollPane for each category
            ScrollPane categoryScrollPane = new ScrollPane();
            categoryScrollPane.setFitToWidth(true);  // Ensure ScrollPane fits width of the container
    
            // Create a VBox for each category inside the ScrollPane
            VBox categoryVBox = new VBox();
            categoryVBox.setSpacing(10);
    
            // Create a label for the category
            Label categoryLabel = new Label(category);
            categoryLabel.setStyle("-fx-font-size: 16px;");
            categoryVBox.getChildren().add(categoryLabel);
    
            // Create an HBox to hold multiple game buttons for the current category
            HBox gameHBox = new HBox();
            gameHBox.setSpacing(10);  // Space between each button
            gameHBox.setPrefHeight(120);  // Fixed height for the HBox
            gameHBox.setStyle("-fx-alignment: center;");
    
            // Add 5 game buttons per category
            for (int i = 0; i < 5; i++) { // You can adjust this number as per your needs
                // Create a button for each game
                Button gameButton = new Button();
                gameButton.setPrefWidth(120);  // Set width of the button
                gameButton.setPrefHeight(120); // Set height of the button
    
                // Create an ImageView for the button image
                ImageView gameImage = new ImageView();
                gameImage.setFitWidth(120);  // Image fit width
                gameImage.setFitHeight(100); // Image fit height
                gameImage.setPreserveRatio(true);  // Maintain aspect ratio
    
                // Temporarily set a blank image or an existing placeholder image (instead of loading from resources)
                // Example: setting a blank image or a solid color as a placeholder
                // gameImage.setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
    
                // Alternatively, use a solid color as placeholder:
                // gameImage.setImage(new Image("file:path/to/default/placeholder.png")); 
    
                // Set the image as the button graphic
                gameButton.setGraphic(gameImage);
    
                // Add the button to the HBox
                gameHBox.getChildren().add(gameButton);
            }
    
            // Add the HBox (containing game buttons) to the category VBox
            categoryVBox.getChildren().add(gameHBox);
    
            // Set the VBox as the content of the ScrollPane
            categoryScrollPane.setContent(categoryVBox);
    
            // Add the ScrollPane to the main VBox that holds all categories
            mainVBox.getChildren().add(categoryScrollPane);
        }
    }    
}
