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

public class GameIntroController implements Initializable {

    private Facade facade = Facade.getInstance();
    private Game currentGame = facade.getCurrentGame();

    @FXML
    private Button exitButton;

    @FXML
    private Label introText;

    @FXML
    private Button nextButton;

    @FXML
    private Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(currentGame.getGameTitle());
        introText.setText(currentGame.getInfo().toString());
    }

    @FXML
    private void goToGameSelect(ActionEvent event) throws IOException {
        facade.selectGame(null);
        App.setRoot("setLangAndDiff");
    }

    @FXML
    private void goToNextGameScreen(ActionEvent event) throws IOException {
        switch(facade.getCurrentGame().getCategory()) {
            case STORY:
                App.setRoot("gameStoryTitleScreen");
                break;
            default:
                App.setRoot("gameDefaultContentScreen");
        }
    }

}
