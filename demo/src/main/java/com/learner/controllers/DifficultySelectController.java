package com.learner.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.learner.game.App;
import com.learner.model.Facade;
import com.learner.model.Language;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DifficultySelectController implements Initializable {

    private final Facade facade = Facade.getInstance();
    private final Language currentLanguage = facade.getCurrentLanguage();

    @FXML
    private Button backButton;

    @FXML
    private Label currentLanguageNameDisplay;
    
    @FXML
    private ImageView easyButton;

    @FXML
    private ImageView hardButton;

    @FXML
    private ImageView mediumButton;

    @FXML
    public void goToEasyGame(MouseEvent event) throws IOException {
        facade.selectLanguage(currentLanguage.getLanguageName());
        facade.selectDifficulty("easy");
        App.setRoot("gameSelect");
    }

    @FXML
    public void goToHardGame(MouseEvent event) throws IOException {
        facade.selectLanguage(currentLanguage.getLanguageName());
        facade.selectDifficulty("hard");
        App.setRoot("gameSelect");
    }

    @FXML
    public void goToMediumGame(MouseEvent event) throws IOException {
        facade.selectLanguage(currentLanguage.getLanguageName());
        facade.selectDifficulty("medium");
        App.setRoot("gameSelect");
    }

    @FXML
    private void goBackToLangSelect(ActionEvent event) {
        try {
            App.setRoot("pickLanguage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentLanguageNameDisplay.setText(currentLanguage.getLanguageName());
    }

}
