package com.learner.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.UUID;

import com.learner.model.Difficulty;
import com.learner.model.Facade;
import com.learner.model.Game;
import com.learner.model.GameManager;
import com.learner.model.innerdata.GameCategory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameSelectionController implements Initializable {

    @FXML private VBox categoryContainer; // Main container for categories
    @FXML private ScrollPane scrollPane;

    private final Facade facade = Facade.getInstance();
    private final GameManager gameManager = GameManager.getInstance();

    private UUID languageUUID;
    private Difficulty difficulty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        languageUUID = facade.getCurrentLanguage().getUUID();
        difficulty = facade.getCurrentDifficulty();
        grabGames();
    }

    private void grabGames() {
        HashMap<GameCategory, ArrayList<Game>> gamesByCategory = gameManager.getGamesByCategory(languageUUID, difficulty);
    
        for (GameCategory category : gamesByCategory.keySet()) {
            // Create a container for each category
            VBox categoryBox = new VBox();
            categoryBox.setSpacing(10);
            categoryBox.getStyleClass().add("category-box");
    
            // Set consistent width for each category
            categoryBox.setPrefWidth(600); // Adjust this value based on your layout
    
            // Category title
            Label categoryLabel = new Label(category.toString());
            categoryLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            categoryBox.getChildren().add(categoryLabel);
    
            // Add games under this category
            for (Game game : gamesByCategory.get(category)) {
                Pane gamePane = createGamePane(game);
                categoryBox.getChildren().add(gamePane);
            }
    
            categoryContainer.getChildren().add(categoryBox);
        }
    }
    

    /**
     * Helper method for game grabber
     * @param game
     * @return
     */
    private Pane createGamePane(Game game) {
        HBox gamePane = new HBox();
        gamePane.setSpacing(15);
        gamePane.getStyleClass().add("game-pane");
    
        // Ensure consistent width for gamePane
        gamePane.setPrefWidth(550); // Adjust based on categoryBox width
    
        // Game icon
        ImageView gameIcon = new ImageView(new Image(getClass().getResourceAsStream("demo\\src\\main\\java\\com\\learner\\controllers\\blank.jpg")));
        gameIcon.setFitWidth(50);
        gameIcon.setFitHeight(50);
        gamePane.getChildren().add(gameIcon);
    
        // Game title
        Label gameTitle = new Label(game.getGameTitle());
        gameTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        gamePane.getChildren().add(gameTitle);
    
        // View or Play Button
        Button viewButton = new Button("View");
        viewButton.getStyleClass().add("view-button");
        gamePane.getChildren().add(viewButton);
    
        // Event handler to open dialog
        viewButton.setOnAction(event -> {
            Dialog<Game> gameDialog = new GameItemDialog(game);
            gameDialog.showAndWait().ifPresent(selectedGame -> {
                System.out.println("Starting game: " + selectedGame.getGameTitle());
            });
        });
    
        return gamePane;
    }
    
}
