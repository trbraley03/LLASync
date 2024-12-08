package com.learner.model.questions;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.learner.model.innerdata.TextObject;

public class FITBQuestion extends Question {
    
    private String answer;

    public FITBQuestion(UUID uuid) {
        super(uuid, QuestionType.FITB);
    }

    @Override
    public void generateQuestion() {
        TextObject textObject = gameManager.findTextObjectByUUID(this.getUUID()); // Retrieve the TextObject using the uuid

        // List of possible gender variations
        List<String> genderVariations = Arrays.asList(textObject.getText(), getGenderVariation(textObject.getText()));

        // Replace the first occurrence of any gender variation with "_____"
        for (String variation : genderVariations) {
            if (textObject.getLinkedText().contains(variation)) {
                this.questionText = textObject.getLinkedText().replaceFirst(variation, "_____");
                this.answer = variation;
                break;
            }
        }
    }

    @Override
    public boolean validateAnswer(String userAnswer) {
        return userAnswer != null && userAnswer.trim().equalsIgnoreCase(answer);
    }

    public String getAnswer() {
        return answer;
    }

    /**
     * Returns the gender variation of the given word.
     * This is a simple example and may need to be extended for more complex cases.
     * @param word the word to get the gender variation for
     * @return the gender variation of the word
     */
    private String getGenderVariation(String word) {
        if (word.endsWith("o")) {
            return word.substring(0, word.length() - 1) + "a";
        } else if (word.endsWith("a")) {
            return word.substring(0, word.length() - 1) + "o";
        }
        return word;
    }
}
