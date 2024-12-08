package com.learner.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.learner.game.App;
import com.learner.model.Facade;
import com.learner.model.Language;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ResultsController implements Initializable {

    private final Facade facade = Facade.getInstance();
    private final Language currentLanguage = Facade.getInstance().getCurrentLanguage();
    private final int numberOfQuestionsCorrect = facade.getNumberOfQuestionsAnsweredCorrectly();
    private final int totalNumberOfQuestions = facade.getNumberOfQuizQuestions();
    private final double questionResultAverage = facade.getQuestionAverageResult();

    @FXML
    private Label title;

    @FXML
    private Label passedFailedText;

    @FXML
    private Label resultsFractionText;

    @FXML
    private ImageView redCheck;

    @FXML
    private ImageView greenCheck;

    @FXML
    private Button goHomeButton;

    @FXML
    private Button newGameButton;

    @FXML
    private Button playAgainButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(facade.getCurrentGame().getGameTitle());

        if(questionResultAverage >= 60) { 
            redCheck.setVisible(false);
            greenCheck.setVisible(true);
            resultsFractionText.setText( numberOfQuestionsCorrect + "/" + totalNumberOfQuestions + " questions correct!");
            facade.addGameToCompletedGames();
        } else {
            redCheck.setVisible(true);
            greenCheck.setVisible(false);
            passedFailedText.setText("Better luck next time!");
            passedFailedText.setTextFill(Color.RED);
            resultsFractionText.setText( numberOfQuestionsCorrect + "/" + totalNumberOfQuestions + " questions correct. Perhaps you could play again!");
        }
    }

    @FXML
    private void goToGameSelect(ActionEvent event) throws IOException {
        App.setRoot("gameSelect");
    }

    @FXML
    private void goToHome(ActionEvent event) throws IOException {
        App.setRoot("main");
    }

    @FXML
    private void playCurrentGameAgain(ActionEvent event) throws IOException {
        App.setRoot("gameIntroScreen");
    }

}
