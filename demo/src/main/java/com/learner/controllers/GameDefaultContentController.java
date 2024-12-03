package com.learner.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.learner.game.App;
import com.learner.model.Facade;
import com.learner.model.Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameDefaultContentController implements Initializable {

    private Facade facade = Facade.getInstance();
    private Game currentGame = facade.getCurrentGame();

    @FXML
    private Button backButton;

    @FXML
    private Label gameContentText;

    @FXML
    private Button nextButton;

    @FXML
    private Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(currentGame.getGameTitle());
        gameContentText.setText(facade.showCurrentTextObject());
    }

    @FXML
    void goBackToPreviousGameScreen(ActionEvent event) throws IOException {
        if(facade.getCurrentTextObjectIndex() == 0) {
            App.setRoot("GameIntroScreen");
        } else {
            facade.getPreviousTextObject();
            gameContentText.setText(facade.showCurrentTextObject());
        }
    }

    @FXML
    void goToNextGameScreen(ActionEvent event) throws IOException {
        if(facade.getCurrentTextObjectIndex() == facade.getMaxTextObjectIndex()) {
            App.setRoot("GameOutroScreen");
        } else {
            facade.getNextTextObject();
            gameContentText.setText(facade.showCurrentTextObject());
        }
    }

}
