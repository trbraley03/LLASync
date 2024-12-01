package com.learner.controllers;

import com.learner.model.Game;

import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameItemDialog extends Dialog<Game> {

    public GameItemDialog(Game game) {

        setTitle(game.getGameTitle()); // Set the title of the dialog to the game title

        // Dialog content
        VBox content = new VBox();
        content.setSpacing(3);  // Space between elements

        // Game title label
        Label nameLabel = new Label(game.getGameTitle());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); // Set title font style

        // Create a square button with an image
        Button playButton = new Button();
        playButton.setPrefWidth(100);  // Set width to 100px (adjust as needed)
        playButton.setPrefHeight(100); // Set height to 100px to make it square

        // Create an ImageView for the button's image
        ImageView buttonImage = new ImageView(new Image("path/to/your/image.png"));  // Adjust the image path as needed
        buttonImage.setFitWidth(80);  // Adjust the image size to fit the button
        buttonImage.setFitHeight(80);
        buttonImage.setPreserveRatio(true);  // Maintain aspect ratio

        // Set the image as the button graphic
        playButton.setGraphic(buttonImage);

        // Add the title label and play button to the content VBox
        content.getChildren().addAll(nameLabel, playButton);

        // Set content for the dialog
        getDialogPane().setContent(content);
    }
}

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.UUID;

// import com.learner.model.Difficulty;
// import com.learner.model.Game;
// import com.learner.model.GameManager;
// import com.learner.model.innerdata.GameCategory;

// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.layout.VBox;
// import javafx.stage.Modality;
// import javafx.stage.Stage;

// public class GameSelectionDialog {

//     private final GameManager gameManager; // Reference to GameManager

//     public GameSelectionDialog(GameManager gameManager) {
//         this.gameManager = gameManager;
//     }

//     /**
//      * Opens the Game Selection Dialog.
//      *
//      * @param languageUUID The UUID of the selected language.
//      * @param difficulty   The selected difficulty level.
//      */
//     public void show(UUID languageUUID, Difficulty difficulty) {
//         Stage dialog = new Stage();
//         dialog.initModality(Modality.APPLICATION_MODAL);
//         dialog.setTitle("Select a Game");

//         // Root layout for the dialog
//         VBox root = new VBox(10); // 10px spacing
//         root.setStyle("-fx-padding: 20; -fx-background-color: white;");

//         // Retrieve games by category
//         HashMap<GameCategory, ArrayList<Game>> gamesByCategory =
//                 gameManager.getGamesByCategory(languageUUID, difficulty);

//         // Create scrollable categories
//         ScrollPane scrollPane = new ScrollPane();
//         VBox categoryContainer = new VBox(10); // Container for all categories
//         categoryContainer.setStyle("-fx-padding: 10;");

//         // Populate categories
//         for (Map.Entry<GameCategory, ArrayList<Game>> entry : gamesByCategory.entrySet()) {
//             GameCategory category = entry.getKey();
//             ArrayList<Game> games = entry.getValue();
//             categoryContainer.getChildren().add(createCategoryBox(category, games));
//         }

//         scrollPane.setContent(categoryContainer);
//         scrollPane.setFitToWidth(true);

//         // Add ScrollPane to the root layout
//         root.getChildren().add(scrollPane);

//         // Set up the dialog scene
//         Scene scene = new Scene(root, 400, 500);
//         dialog.setScene(scene);
//         dialog.showAndWait();
//     }

//     /**
//      * Helper method to create a VBox for a game category.
//      *
//      * @param category The game category.
//      * @param games    The games in this category.
//      * @return A VBox containing the category and its games.
//      */
//     private VBox createCategoryBox(GameCategory category, ArrayList<Game> games) {
//         VBox categoryBox = new VBox(5);
//         categoryBox.setStyle("-fx-border-color: lightgray; -fx-padding: 10; -fx-background-color: #f9f9f9;");

//         // Category label
//         Label categoryLabel = new Label(category.getCategory());
//         categoryLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

//         // Games list
//         VBox gamesBox = new VBox(3);
//         for (Game game : games) {
//             Button gameButton = new Button(game.getGameTitle());
//             gameButton.setStyle("-fx-font-size: 14; -fx-cursor: hand;");
//             gameButton.setOnAction(event -> onGameSelected(game));
//             gamesBox.getChildren().add(gameButton);
//         }

//         categoryBox.getChildren().addAll(categoryLabel, gamesBox);
//         return categoryBox;
//     }

//     /**
//      * Callback when a game is selected.
//      *
//      * @param game The selected game.
//      */
//     private void onGameSelected(Game game) {
//         System.out.println("Game selected: " + game.getGameTitle());
//         // further actions for game selection ... 
//     }
// }
