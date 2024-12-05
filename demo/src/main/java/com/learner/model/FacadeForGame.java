package com.learner.model;


import java.util.UUID;

import com.learner.model.innerdata.TextObject;
import com.learner.model.questions.Question;

public class FacadeForGame {

    private Game currentGame;

    public void selectGame(Game game) {
        currentGame = game;
    }

    public int getCurrentTextObjectIndex() {
        return currentGame.getCurrentTextObjectIndex();
    }

    public String showCurrentTextObject() {
        TextObject textObject = currentGame.getCurrentTextObject();
        return formatTextObjectContent(textObject);
    }

    public String getNextTextObject() {
        currentGame.getNextTextObject();
        return showCurrentTextObject();
    }

    public String getPreviousTextObject() {
        currentGame.getPreviousTextObject();
        return showCurrentTextObject();
    }

    public int getMaxTextObjectIndex() {
        return currentGame.getMaxTextObjectIndex();
    }

    public void setTextObjectIndex(int newIndex) {
        currentGame.setTextObjectIndex(newIndex);
    }

    // Store language in the game, switch how its being accessed from facade
    private String formatTextObjectContent(TextObject textObject) {
        return String.format(Facade.getInstance().getCurrentLanguage().getLanguageName() + ": %s\nEnglish: %s\nExample: %s\nHelper: %s",
                textObject.getText(),
                textObject.getEnglishText(),
                textObject.getLinkedText(),
                textObject.getHelperText());
    }

    public Question startQuiz() {
        return currentGame.startQuiz();
    }

    public Question getQuizQuestion() {
        return currentGame.getQuizQuestion();
    }

    public Question getNextQuizQuestion() {
        return currentGame.getNextQuizQuestion();
    }

    public boolean validateQuizAnswer(String answer) {
        return currentGame.validateQuizAnswer(answer);
    }

    public String endGameSession(User user, UUID languageUUID) {
        if (user != null && currentGame != null) {
            user.getProgressTracker(languageUUID).addCompletedGame(currentGame.getUUID());
        }
        currentGame = null;
        return "Game session ended. Progress saved.";
    }

}

