package com.learner.controllers;

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
import javafx.scene.layout.VBox;

public class GameSelectController implements Initializable {

    Language currentLanguage;
    Difficulty currentDifficulty;
    Facade facade = Facade.getInstance();

    @FXML
    private VBox mainVbox;

    @FXML
    private Button backButton;

    @FXML
    private Label title; // Language + difficulty + "games"

    // private HashMap<String, ScrollPane> categoryScrollPanes; // ScrollPane (linked to category)

    // private HashMap<String, HBox> categoryHBoxes; // Hbox (linked to category)

    // private HashMap<String, ArrayList<VBox>> gameBoxes; // ArrayList of VBoxes holding title and button w/ image (linked to category)

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        currentLanguage = facade.getCurrentLanguage();
        currentDifficulty = facade.getCurrentDifficulty();

        for(Game game : facade.getAvailableGames()) {
            Button gameButton = new Button(game.getGameTitle());

            // Assign UUID to the button 
            UUID gameUUID = game.getUUID();
            gameButton.setUserData(gameUUID);

            // Add the button to your VBox or other container
            mainVbox.getChildren().add(gameButton);

            // Set up the event handler
            gameButton.setOnAction(event -> {
                UUID clickedUUID = (UUID) gameButton.getUserData();
                // System.out.println("Button clicked! UUID: " + clickedUUID + " " + game.getGameTitle());
                facade.selectGame(clickedUUID);
                switch(facade.getCurrentGame().getCategory()) {
                    case GameCategory.STORY:
                }
            });
        }
        title.setText(currentDifficulty.getLabel() + " " + currentLanguage.getLanguageName() + " Games");
    }

    @FXML
    public void goToSetLangAndDiff(ActionEvent event) throws IOException{
        App.setRoot("setLangAndDiff");
    }
}
