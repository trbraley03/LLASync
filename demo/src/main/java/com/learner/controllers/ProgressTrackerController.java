package com.learner.controllers;

import java.io.IOException;
import java.util.UUID;

import com.learner.game.App;
import com.learner.model.Facade;
import com.learner.model.User.ProgressTracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class ProgressTrackerController {

    @FXML
    private Button backButton;

    @FXML
    private ProgressBar gamesCompletedBar;

    private static final int TOTAL_GAMES = 20;
    private ProgressTracker progressTracker;

    public ProgressTrackerController() {
        // Access current user's progress tracker
        Facade facade = Facade.getInstance();
        progressTracker = facade.getCurrentUser().getProgressTracker(facade.getCurrentLanguage().getUUID());
    }

    @FXML
    public void initialize() {
        updateProgressBar();
    }

    @FXML
    void goToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    private void updateProgressBar() {
        int completedGames = progressTracker.getTotalCompletedGames();
        double progress = (double) completedGames / TOTAL_GAMES;
        gamesCompletedBar.setProgress(progress);
    }

    public void onGameCompleted(UUID gameUUID) {
        progressTracker.addCompletedGame(gameUUID);
        updateProgressBar();
    }
}
