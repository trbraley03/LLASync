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
    private ImageView frenchButton;

    @FXML
    private Button homeButton;

    @FXML
    private ImageView spanishButton;

    private Facade facade = Facade.getInstance();

    @FXML
    void goToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void selectFilipino(MouseEvent event) {
        facade.selectLanguage("filipino");
        // make it go to diffuculty page
    }

    @FXML
    void selectFrench(MouseEvent event) {
        facade.selectLanguage("french");
        // make it go to diffuculty page
    }

    @FXML
    void selectSpanish(MouseEvent event) {
        facade.selectLanguage("spanish");
        // make it go to diffuculty page
    }

}
