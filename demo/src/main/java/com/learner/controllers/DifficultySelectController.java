package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;
import com.learner.model.Facade;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DifficultySelectController {

    private final Facade facade = Facade.getInstance();
    
    @FXML
    private ImageView easyButton;

    @FXML
    private ImageView hardButton;

    @FXML
    private ImageView mediumButton;

    @FXML
    public void goToEasyGame(MouseEvent event) throws IOException {
        facade.selectLanguage("filipino");
        facade.selectDifficulty("easy");
        App.setRoot("gameSelect");
    }

    @FXML
    public void goToHardGame(MouseEvent event) throws IOException {
        facade.selectLanguage("filipino");
        facade.selectDifficulty("hard");
        App.setRoot("gameSelect");
    }

    @FXML
    public void goToMediumGame(MouseEvent event) throws IOException {
        facade.selectLanguage("filipino");
        facade.selectDifficulty("medium");
        App.setRoot("gameSelect");
    }

}
