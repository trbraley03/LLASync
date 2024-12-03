package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;
import com.learner.model.Facade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Temporary Controller until screens are completed
 * Two text boxes for setting language & difficulty
 */
public class LangAndDiffController {

    Facade facade = Facade.getInstance();

    @FXML
    private TextField difficultyBox;

    @FXML
    private TextField languageBox;

    @FXML
    private Button backButton;

    @FXML
    private Button setLangAndDiff;

    // Variables to store input values
    private String difficulty;
    private String language;

    @FXML
    private void goToGameSelect(ActionEvent event) throws IOException {
        language = languageBox.getText().toLowerCase();
        difficulty = difficultyBox.getText().toLowerCase();
        facade.selectLanguage(language);
        facade.selectDifficulty(difficulty);
        if(facade.getCurrentDifficulty() == null || facade.getCurrentLanguage() == null) {
            // System.out.println("Retry");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Invalid");
            alert.setHeaderText(null);
            alert.setContentText("Language or difficulty is invalid.");
            alert.showAndWait();
        } else {
            App.setRoot("gameSelect");
        }
    }

    @FXML
    private void goToMain(ActionEvent event) throws IOException {
        App.setRoot("main");
    }

}
