package com.learner.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.learner.game.App;
import com.learner.model.Facade;
import com.learner.model.questions.FITBQuestion;
import com.learner.model.questions.MatchingQuestion;
import com.learner.model.questions.MultipleChoiceQuestion;
import com.learner.model.questions.Question;
import com.learner.model.questions.SequencingQuestion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameOutroController implements Initializable {

    Facade facade = Facade.getInstance();

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
        facade.setTextObjectIndex(0);
        App.setRoot("gameIntroScreen");
    }

    @FXML
    private void startQuiz(ActionEvent event) throws IOException {
        Question question = facade.startQuiz();
        directQuestion(question);
    }
 
    public void directQuestion(Question question) throws IOException {
        if(question instanceof SequencingQuestion) {
            App.setRoot("sequencingQuestion");
        } else if (question instanceof FITBQuestion) {
            App.setRoot("fitbQuestion");
        } else if (question instanceof MatchingQuestion) {
            App.setRoot("matchingQuestion");
        } else if (question instanceof MultipleChoiceQuestion) {
            App.setRoot("multipleChoiceQuestion");
        } else {
            System.err.println("none");
        }
    }

}
