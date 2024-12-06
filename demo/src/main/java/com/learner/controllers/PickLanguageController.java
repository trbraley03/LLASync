package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;
import com.learner.model.Facade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PickLanguageController {

    @FXML
    private ImageView filipinoButton;

    @FXML
    private ImageView portugueseButton;

    @FXML
    private Button homeButton;

    @FXML
    private ImageView spanishButton;

    private Facade facade = Facade.getInstance();

    @FXML
    private void goToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void selectFilipino(MouseEvent event) throws IOException {
        facade.selectLanguage("filipino");
        App.setRoot("setDifficulty");
    }

    @FXML
    private void selectPortuguese(MouseEvent event) throws IOException {
        facade.selectLanguage("portuguese");
        App.setRoot("setDifficulty");
    }

    @FXML
    private void selectSpanish(MouseEvent event) throws IOException {
        facade.selectLanguage("spanish");
        App.setRoot("setDifficulty");
    }

}
