package com.learner.controllers.questions;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.learner.model.Facade;
import com.learner.model.questions.MultipleChoiceQuestion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MultipleChoiceQuestionController implements Initializable {

    Facade facade = Facade.getInstance();

    private MultipleChoiceQuestion currentQuestion;
    private String selectedAnswer; 

    @FXML
    private HBox hboxForChoiceButtons;

    @FXML
    private Button nextButton;

    @FXML
    private Label questionText;

    @FXML
    private Text questionTypeText;

    @FXML
    private Button submitButton;

    @FXML
    private Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set the game title in the UI
        title.setText(facade.getCurrentGame().getGameTitle());

        // Load and display the first question
        loadQuestion();
    }

    private void loadQuestion() {
        currentQuestion = (MultipleChoiceQuestion) facade.getQuizQuestion();
    
        if (currentQuestion != null) {
            questionText.setText(currentQuestion.getQuestionText());
            ArrayList<String> options = currentQuestion.getOptions();
    
            questionTypeText.setText(options.size() == 2 ? "True/False" : "Multiple Choice");
    
            // hboxForChoiceButtons.getChildren().clear(); // Clear previous buttons
    
            for (String option : options) {
                Button optionButton = new Button(option);
    
                // Assign event handler for selection
                optionButton.setOnAction(event -> handleOptionSelection(option));
    
                // Add button to the UI
                hboxForChoiceButtons.getChildren().add(optionButton);
            }
        }
    }
    
    @FXML
    private void submitQuestion(ActionEvent event) throws IOException {
        if (currentQuestion != null && selectedAnswer != null) {
            boolean isCorrect = currentQuestion.validateAnswer(selectedAnswer);
    
            // Change button colors based on correctness
            hboxForChoiceButtons.getChildren().forEach(node -> {
                if (node instanceof Button) {
                    Button btn = (Button) node;
                    String answerText = btn.getText();
    
                    // Check / compare button to answers 
                    if (answerText.equals(currentQuestion.getCorrectAnswer()) && answerText.equals(selectedAnswer)) {
                        btn.setStyle("-fx-background-color: green; -fx-text-fill: white;"); // Correct answer
                    } else if (answerText.equals(selectedAnswer)) {
                        btn.setStyle("-fx-background-color: red; -fx-text-fill: white;"); // Incorrect selected answer
                    } else {
                        btn.setStyle(""); // Reset style for other buttons
                    }
                    
                    // Disable the button after submission
                    btn.setDisable(true); // Disable the button so it can't be clicked again
                }
            });
        }
    }
    
    private void handleOptionSelection(String option) {
        selectedAnswer = option; // Store the selected answer
    }
    

    @FXML
    private void goToNext(ActionEvent event) throws IOException {
        // Navigate to the next part of the application (e.g., results or another question type)
        System.out.println("Next button clicked.");
    }
}
