package com.learner.controllers.questions;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.learner.controllers.GameOutroController;
import com.learner.game.App;
import com.learner.model.Facade;
import com.learner.model.questions.FITBQuestion;
import com.learner.narration.Narrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class FITBQuestionController implements Initializable {

    private final Facade facade = Facade.getInstance();
    private FITBQuestion currentQuestion = (FITBQuestion) facade.getQuizQuestion();
    private String selectedAnswer; 
    private boolean spokenFeedback = facade.getCurrentUser().getReadQuestionFeedbackAloud();
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

    @FXML
    private ImageView audioButton;

    @FXML
    private ImageView exitButton;

    @FXML
    private HBox hboxForChoiceButtons;

    @FXML
    private void playAudio(MouseEvent event) {
        Narrator.playSound(questionText.getText());
    }
    
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
        // boolean isCorrect = currentQuestion.validateAnswer(selectedAnswer); // incorrect use
        boolean isCorrect = facade.validateQuizAnswer(selectedAnswer);

        if(isCorrect) {
            correctAnswerDisplayText.setText(getEncouragement());
            correctOrIncorrectText.setVisible(true);
            correctAnswerDisplayText.setVisible(true);
            if(spokenFeedback) Narrator.playSound("Correct! Well done.");
        } else {
            correctOrIncorrectText.setText("Incorrect");
            correctOrIncorrectText.setStyle("-fx-fill: red;");
            correctAnswerDisplayText.setText("Expected Answer: " + currentQuestion.getAnswer());
            correctOrIncorrectText.setVisible(true);
            correctAnswerDisplayText.setVisible(true);
            if(spokenFeedback) Narrator.playSound("Incorrect! Better luck next time.");
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

    @FXML
    private void goToMain(MouseEvent event) throws IOException {
        App.setRoot("main");
    }

}
