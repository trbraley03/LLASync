package com.learner.controllers.questions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.learner.controllers.GameOutroController;
import com.learner.model.Facade;
import com.learner.model.questions.FITBQuestion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FITBQuestionController implements Initializable {

    private final Facade facade = Facade.getInstance();
    private FITBQuestion currentQuestion = (FITBQuestion) facade.getQuizQuestion();
    private String selectedAnswer; 

    @FXML
    private TextField answerBox;

    @FXML
    private Text correctAnswerDisplayText;

    @FXML
    private Text correctOrIncorrectText;

    @FXML
    private Label questionText;

    @FXML
    private Button submit;

    @FXML
    private Label title;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionText.setText(currentQuestion.getQuestionText());
    }

    @FXML
    private void submitQuestion(ActionEvent event) throws IOException {
        if(submit.getText().equals("Continue")) {
            continueButton();
        } else {
            submitButton();
        }
    } 

    private void submitButton() {
        answerBox.setEditable(false);
        selectedAnswer = answerBox.getText();
        boolean isCorrect = currentQuestion.validateAnswer(selectedAnswer);

        if(isCorrect) {
            correctOrIncorrectText.setVisible(true);
        } else {
            correctOrIncorrectText.setText("Incorrect");
            correctOrIncorrectText.setStyle("-fx-fill: red;");
            correctAnswerDisplayText.setText("Expected Answer: " + currentQuestion.getAnswer());
            correctOrIncorrectText.setVisible(true);
            correctAnswerDisplayText.setVisible(true);
        }
        submit.setText("Continue");
    }

    private void continueButton() throws IOException {
        GameOutroController.directQuestion(facade.getNextQuizQuestion());
    }

}
