package com.learner.controllers.questions;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
    private String[] encouragement = {"Great job!", "Keep it up!", "You're doing great!"};

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
        title.setText(facade.getCurrentGame().getGameTitle());
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
            correctAnswerDisplayText.setText(getEncouragement());
            correctOrIncorrectText.setVisible(true);
            correctAnswerDisplayText.setVisible(true);
        } else {
            correctOrIncorrectText.setText("Incorrect");
            correctOrIncorrectText.setStyle("-fx-fill: red;");
            correctAnswerDisplayText.setText("Expected Answer: " + currentQuestion.getAnswer());
            correctOrIncorrectText.setVisible(true);
            correctAnswerDisplayText.setVisible(true);
        }
        submit.setText("Continue");
    }

    public String getEncouragement() {
        Random random = new Random();
        int index = random.nextInt(encouragement.length); // Generate a random index
        return encouragement[index];
    }

    private void continueButton() throws IOException {
        GameOutroController.directQuestion(facade.getNextQuizQuestion());
    }

}
