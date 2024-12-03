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

public class GameOutroController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Label gameContentText;

    @FXML
    private Button restartGameButton;

    @FXML
    private Button startQuizButton;

    @FXML
    private Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(Facade.getInstance().getCurrentGame().getGameTitle());
    }

    @FXML
    private void goBackToPreviousGameScreen(ActionEvent event) throws IOException {
        App.setRoot("gameDefaultContentScreen");
    }

    @FXML
    private void goToGameIntro(ActionEvent event) throws IOException {
        Facade.getInstance().setTextObjectIndex(0);
        App.setRoot("gameIntroScreen");
    }

    @FXML
    private void startQuiz(ActionEvent event) throws IOException {

    }

}
