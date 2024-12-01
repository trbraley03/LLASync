package com.learner.model;


import java.util.UUID;

import com.learner.model.innerdata.TextObject;
import com.learner.model.questions.Question;

public class FacadeForGame {

    private Game currentGame;
    private int currentTextObjectIndex;
    private int currentQuizIndex;

    public void selectGame(Game game) {
        currentGame = game;
    }

    public String showCurrentTextObject() {
        if (currentGame == null || currentGame.getTextObjects().isEmpty()) return "No content available.";
        TextObject textObject = currentGame.getTextObjects().get(currentTextObjectIndex);
        return formatTextObjectContent(textObject);
    }

    public String nextTextObject() {
        if (currentGame == null || currentTextObjectIndex >= currentGame.getTextObjects().size() - 1) {
            return null;
        }
        currentTextObjectIndex++;
        return showCurrentTextObject();
    }

    public String previousTextObject() {
        if (currentGame == null || currentTextObjectIndex <= 0) {
            return null;
        }
        currentTextObjectIndex--;
        return showCurrentTextObject();
    }

    private String formatTextObjectContent(TextObject textObject) {
        return String.format("Filipino: %s\nEnglish: %s\nExample: %s\nHelper: %s",
                textObject.getText(),
                textObject.getEnglishText(),
                textObject.getLinkedText(),
                textObject.getHelperText());
    }

    public String startQuiz() {
        if (currentGame == null) return "No game selected.";
        currentGame.pullQuestions();
        currentQuizIndex = 0;
        return "Quiz started. Answer the following questions.";
    }

    public String getNextQuizQuestion() {
        if (currentGame == null) return "No game selected.";
        Question question = currentGame.getQuestion(currentQuizIndex++);
        return (question != null) ? question.getQuestionText() : "Quiz complete!";
    }

    public boolean validateQuizAnswer(String answer) {
        Question question = currentGame.getQuestion(currentQuizIndex - 1);
        return question != null && question.validateAnswer(answer);
    }

    public String endGameSession(User user, UUID languageUUID) {
        if (user != null && currentGame != null) {
            user.getProgressTracker(languageUUID).addCompletedGame(currentGame.getUUID());
        }
        currentGame = null;
        return "Game session ended. Progress saved.";
    }

}

