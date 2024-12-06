package com.learner.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import com.learner.game.App;
import com.learner.model.Difficulty;
import com.learner.model.Facade;
import com.learner.model.Game;
import com.learner.model.Language;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GameSelectController implements Initializable {

    private final Facade facade = Facade.getInstance();
    private final Language currentLanguage = facade.getCurrentLanguage();
    private final Difficulty currentDifficulty = facade.getCurrentDifficulty();

    @FXML
    private VBox mainVbox;

    @FXML
    private Button backButton;

    @FXML
    private Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Game game : facade.getAvailableGames()) {
            Button gameButton = new Button();
            String gameTitle = game.getGameTitle();
            UUID gameUUID = game.getUUID();
            gameButton.setUserData(gameUUID);
            gameButton.setMaxWidth(98);
            gameButton.setMaxHeight(85);
            gameButton.setPrefWidth(98);
            gameButton.setPrefHeight(85);
            gameButton.setMinWidth(100);
            gameButton.setMinHeight(80);

            // Check if the image file exists
            String imagePath = "/com/learner/game/game-select-icons/" + gameTitle + ".png";
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl != null) {
                // Set the button's graphic to the image
                ImageView imageView = new ImageView(new Image(imageUrl.toString()));
                imageView.setFitWidth(100);  // Adjust the width as needed
                imageView.setFitHeight(100); // Adjust the height as needed
                imageView.setPreserveRatio(true);
                gameButton.setGraphic(imageView);
            } else {
                // Fallback to setting the button's text to the game name
                gameButton.setText(gameTitle);
                System.out.println("image file not found:" + gameTitle);
            }

            // Add the button to your VBox or other container
            mainVbox.getChildren().add(gameButton);

            // Set up the event handler
            gameButton.setOnAction(event -> {
                UUID clickedUUID = (UUID) gameButton.getUserData();
                facade.selectGame(clickedUUID);
                try {
                    goToGameIntroduction(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        title.setText(currentDifficulty.getLabel() + " " + currentLanguage.getLanguageName() + " Games");
    }

    @FXML
    private void goToSetLangAndDiff(ActionEvent event) throws IOException {
        Difficulty reset = null;
        facade.selectDifficulty(reset);
        App.setRoot("setDifficulty");   
    }

    @FXML
    private void goToGameIntroduction(ActionEvent event) throws IOException {
        App.setRoot("gameIntroScreen");
    }
}

