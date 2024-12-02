package com.learner.controllers;

import java.io.IOException;
import java.util.ArrayList;

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
        ArrayList<String> languages = facade.getAvailableLanguages();
        int filipinoIndex = languages.indexOf("filipino");
        if (filipinoIndex != -1) {
            String result = facade.selectLanguage(filipinoIndex);
            System.out.println(result);
        } else {
            System.out.println("Filipino language not found.");
        }
    }

    @FXML
    void selectFrench(MouseEvent event) {
        ArrayList<String> languages = facade.getAvailableLanguages();
        int frenchIndex = languages.indexOf("french");
        if (frenchIndex != -1) {
            String result = facade.selectLanguage(frenchIndex);
            System.out.println(result);
        } else {
            System.out.println("French language not found.");
        }
    }

    @FXML
    void selectSpanish(MouseEvent event) {
        ArrayList<String> languages = facade.getAvailableLanguages();
        int spanishIndex = languages.indexOf("spanish");
        if (spanishIndex != -1) {
            String result = facade.selectLanguage(spanishIndex);
            System.out.println(result);
        } else {
            System.out.println("Spanish language not found.");
        }
    }

}