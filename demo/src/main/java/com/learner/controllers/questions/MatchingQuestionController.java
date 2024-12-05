package com.learner.controllers.questions;

import java.net.URL;
import java.util.ResourceBundle;

import com.learner.model.Facade;
import com.learner.model.questions.MatchingQuestion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MatchingQuestionController implements Initializable {

    private final Facade facade = Facade.getInstance();
    private MatchingQuestion currentQuestion = (MatchingQuestion) facade.getQuizQuestion();

    @FXML
    private ImageView exitButton;

    @FXML
    private Button leftButton1;

    @FXML
    private Button leftButton2;

    @FXML
    private Button leftButton3;

    @FXML
    private Button rightButton1;

    @FXML
    private Button rightButton2;

    @FXML
    private Button rightButton3;

    @FXML
    private Button submit;

    @FXML
    private Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(facade.getCurrentGame().getGameTitle());
        loadQuestion();
    }

    private void loadQuestion() {

    }

    @FXML
    void submitQuestion(ActionEvent event) {
        
    }

}

