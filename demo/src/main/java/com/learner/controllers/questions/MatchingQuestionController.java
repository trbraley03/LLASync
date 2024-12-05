package com.learner.controllers.questions;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import com.learner.model.Facade;
import com.learner.model.questions.MatchingQuestion;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MatchingQuestionController implements Initializable {

    private final Facade facade = Facade.getInstance();
    private MatchingQuestion currentQuestion = (MatchingQuestion) facade.getQuizQuestion();

    private final HashMap<Button, String> leftButtonMap = new HashMap<>();
    private final HashMap<Button, String> rightButtonMap = new HashMap<>();

    private Button buttonClicked1;
    private Button buttonClicked2;

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
        // Get data from the MatchingQuestion
        ArrayList<String> leftSide = currentQuestion.getLeftSide();
        ArrayList<String> rightSide = currentQuestion.getRightSide();

        // Map left-side words to left buttons
        List<Button> leftButtons = List.of(leftButton1, leftButton2, leftButton3);
        for (int i = 0; i < 3; i++) {
            Button button = leftButtons.get(i);
            button.setText(leftSide.get(i));
            leftButtonMap.put(button, leftSide.get(i));
        }

        // Map shuffled right-side words to right buttons
        List<Button> rightButtons = List.of(rightButton1, rightButton2, rightButton3);
        for (int i = 0; i < 3; i++) {
            Button button = rightButtons.get(i);
            button.setText(rightSide.get(i));
            rightButtonMap.put(button, rightSide.get(i));
        }
    }

    @FXML
    void submitQuestion(ActionEvent event) {
        // Validate answers
        boolean allCorrect = true;

        // Check matches between left and right buttons
        for (Button leftButton : leftButtonMap.keySet()) {
            String leftWord = leftButtonMap.get(leftButton);
            String selectedMeaning = getSelectedMeaningFor(leftButton);

            // Assuming validateAnswer only checks the provided meaning
            if (!currentQuestion.validateAnswer(selectedMeaning)) {
                allCorrect = false;
                break;
            }
        }

        // Handle the result
        if (allCorrect) {
            try {
                continueButton();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Incorrect answers! Try again.");
        }
    }

    private void continueButton() throws IOException {
        
    }

    private String getSelectedMeaningFor(Button leftButton) {
        return null;
    }

    /**
     * Switch the positions of two buttons with animation.
     *
     * @param button1 The first button to switch
     * @param button2 The second button to switch
     */
    private void animateSwitch(Button button1, Button button2) {
        // Get initial positions
        double button1X = button1.getLayoutX();
        double button1Y = button1.getLayoutY();
        double button2X = button2.getLayoutX();
        double button2Y = button2.getLayoutY();

        // Create TranslateTransition for button1
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1), button1);
        transition1.setByX(button2X - button1X);
        transition1.setByY(button2Y - button1Y);

        // Create TranslateTransition for button2
        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), button2);
        transition2.setByX(button1X - button2X);
        transition2.setByY(button1Y - button2Y);

        // Combine both animations into a ParallelTransition
        ParallelTransition parallelTransition = new ParallelTransition(transition1, transition2);

        // Play the animation
        parallelTransition.play();

        // Update positions after the animation to maintain correct state
        parallelTransition.setOnFinished(event -> {
            button1.setLayoutX(button2X);
            button1.setLayoutY(button2Y);
            button2.setLayoutX(button1X);
            button2.setLayoutY(button1Y);
        });
    }
}
