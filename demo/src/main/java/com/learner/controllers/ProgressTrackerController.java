package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class ProgressTrackerController {

    @FXML
    private Button backButton;

    @FXML
    private ProgressBar gamesCompletedBar;

    @FXML
    private ProgressBar gamesTotalBar;

    @FXML
    void goToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

}
