package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;
import com.learner.model.Difficulty;
import com.learner.model.Facade;
import com.learner.model.Language;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class GameStoryContentController {

    private final Facade facade = Facade.getInstance();
    private final Language currentLanguage = facade.getCurrentLanguage();
    private final Difficulty currentDifficulty = facade.getCurrentDifficulty();

    @FXML
    private Button backButton;

    @FXML
    private Label gameContentText;

    @FXML
    private Button nextButton;

    @FXML
    private ImageView storyImage;

    @FXML
    private Button translateButton;

    @FXML
    private void goBackToPreviousGameScreen(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void goToNextGameScreen(ActionEvent event) throws IOException  {

    }

    @FXML
    private void translateText(ActionEvent event) throws IOException  {

    }

}
