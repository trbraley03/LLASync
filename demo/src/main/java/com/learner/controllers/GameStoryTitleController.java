package com.learner.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.learner.game.App;
import com.learner.model.Facade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameStoryTitleController implements Initializable {

    private Facade facade = Facade.getInstance();

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {    
        title.setText(facade.getCurrentGame().getGameTitle());
    }

    @FXML
    private void goBackToPreviousGameScreen(ActionEvent event) throws IOException {
        App.setRoot("gameIntroScreen");
    }

    @FXML
    private void goToNextGameScreen(ActionEvent event) throws IOException {
        App.setRoot("gameStoryContentScreen");
    }

}
