package com.learner.model.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import com.learner.model.Game;
import com.learner.model.innerdata.TextObject;

/**
 * MatchingQuestion presents a list of items on the left and a randomized list
 * of matching options on the right. The user is asked to match each item on
 * the left with the correct option on the right.
 */
public class MatchingQuestion extends Question {

    private final ArrayList<String> leftSide;        // The words to match
    private final ArrayList<String> rightSide;       // The meanings, shuffled for display
    private final HashMap<String, String> correctPairs; // Maps each word to its correct meaning

    /**
     * Constructs a MatchingQuestion with a unique identifier and type set to MATCHING.
     * 
     * @param uuid Unique identifier for the question
     */
    public MatchingQuestion(UUID uuid) {
        super(uuid, QuestionType.MATCHING);
        this.leftSide = new ArrayList<>();
        this.rightSide = new ArrayList<>();
        this.correctPairs = new HashMap<>();
    }

    /**
     * Generates the question by populating the left and right lists of items.
     * The correct pairs are stored in correctPairs, and rightSide is shuffled for display.
     * 
     * @param textObjects List of TextObject items representing the matching pairs.
     */
    @Override
    public void generateQuestion() {
        ArrayList<TextObject> textObjects = new ArrayList<>();
        TextObject theTextObject = gameManager.findTextObjectByUUID(this.getUUID());
        textObjects.add(theTextObject);
        Game game = gameManager.findGameByUUID(gameUUID);

        // Populate with additional text objects
        for (int i = 0; i < 3; i++) {
            theTextObject = game.getNextTextObject(theTextObject.getUUID());
            if(!textObjects.contains(theTextObject) || !(textObjects.size() < 3)) {
                textObjects.add(theTextObject);
            }
        }

        // Populate leftSide, rightSide, and correctPairs
        for (TextObject textObject : textObjects) {
            String word = textObject.getText();
            String meaning = textObject.getEnglishText();

            leftSide.add(word);      // Add the word to the left side
            rightSide.add(meaning);  // Add the meaning to the right side
            correctPairs.put(word, meaning);  // Store the correct pair
        }

        // Debugging: Print the rightSide list before shuffling
        System.out.println("Right side before shuffling: " + rightSide);

        // Shuffle rightSide for randomized options
        Collections.shuffle(rightSide);

        // Debugging: Print the rightSide list after shuffling
        System.out.println("Right side after shuffling: " + rightSide);

        // Build the question text without numbering or letters
        StringBuilder questionBuilder = new StringBuilder("Match each word with its correct meaning:\n");
        questionBuilder.append("Words:\n");
        for (String word : leftSide) {
            questionBuilder.append("- ").append(word).append("\n");
        }
        questionBuilder.append("\nMeanings:\n");
        for (String meaning : rightSide) {
            questionBuilder.append("- ").append(meaning).append("\n");
        }

        this.questionText = questionBuilder.toString();
    }

    /**
     * Validates the user's answer by comparing it to the correct pairs.
     * 
     * @param userAnswer The answer provided by the user as a mapping of word to meaning.
     * @return true if the user's answer matches the correct pairs, false otherwise.
     */
    @Override
    public boolean validateAnswer(String userAnswer) {
        // Expected format: "word:meaning, word2:meaning2"
        String[] pairs = userAnswer.split(", ");
        if (pairs.length != leftSide.size()) {
            return false;
        }

        for (String pair : pairs) {
            String[] match = pair.split(":");
            if (match.length != 2) {
                return false; // Invalid format if not in "word:meaning" format
            }

            String word = match[0].trim();
            String selectedMeaning = match[1].trim();

            // Check if the selected meaning matches the correct one in correctPairs
            if (!correctPairs.getOrDefault(word, "").equalsIgnoreCase(selectedMeaning)) {
                return false;
            }
        }

        return true; // All pairs matched correctly
    }

    /**
     * Gets the list of items on the left side (e.g., words to match).
     * 
     * @return The leftside items in the question.
     */
    public ArrayList<String> getLeftSide() {
        return leftSide;
    }

    /**
     * Gets the list of items on the right side (e.g., meanings or translations).
     * 
     * @return The rightside items in the question, in randomized order.
     */
    public ArrayList<String> getRightSide() {
        return rightSide;
    }

    public HashMap<String, String> getCorrectPairs() {
        return correctPairs;
    }
}