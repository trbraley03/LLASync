package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;
import com.learner.model.Facade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ProgressTrackerController {

    private Facade facade = Facade.getInstance();

    @FXML
    private Button backButton;

    @FXML
    private Label progressLabel;

    @FXML
    private ProgressBar gamesCompletedBar;

    @FXML
    public void initialize() {
        updateProgressBar();
    }

    @FXML
    void goToHome(ActionEvent event) throws IOException {
        App.setRoot("main");
    }

    private void updateProgressBar() {
        int totalGames = facade.getTotalNumberOfGames();
        int completedGames = facade.getTotalNumberOfCompletedGames();
        progressLabel.setText(facade.getTotalNumberOfCompletedGames() + " out of " + facade.getTotalNumberOfGames() + " completed.");
        double progress = 0.0;
        if (completedGames != 0) {
            progress = (double) completedGames / (double) totalGames;
        }
        System.out.println("Progress: " + progress);
        gamesCompletedBar.setProgress(progress);
    }
}
