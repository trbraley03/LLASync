package com.learner.controllers.questions;

import com.learner.model.Facade;
import com.learner.model.questions.MatchingQuestion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MatchingQuestionController {

    private final Facade facade = Facade.getInstance();
    private MatchingQuestion currentQuestion = (MatchingQuestion) facade.getQuizQuestion();

    @FXML
    private ImageView exitButton;

    @FXML
    private HBox hboxForChoiceButtons;

    @FXML
    private Button leftButton1;

    @FXML
    private Button leftButton2;

    @FXML
    private Button leftButton3;

    @FXML
    private Text questionTypeText;

    @FXML
    private Button rightButton1;

    @FXML
    private Button rightButton2;

    @FXML
    private Button rightButton3;

    @FXML
    private Button sumbit;

    @FXML
    private Label title;

    @FXML
    void sumbitQuestion(ActionEvent event) {

    }

}

