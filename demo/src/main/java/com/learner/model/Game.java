package com.learner.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import com.learner.model.innerdata.GameCategory;
import com.learner.model.innerdata.GameInfo;
import com.learner.model.innerdata.TextObject;
import com.learner.model.questions.Question;
import com.learner.model.questions.QuestionFactory;
import com.learner.model.questions.QuestionType;

public class Game {

    // General info for game
    private final UUID languageUUID;
    private final String gameTitle;
    private final Difficulty difficulty;
    private final UUID uuid;            
    private final GameCategory category;
    private final ArrayList<TextObject> textObjects; // Stores TextObject instances for this game
    private final ArrayList<Question> questions;     // Stores Question instances for this game
    private final GameInfo info;

    // Info for an active game
    private final ArrayList<Question> quizQuestions;
    private int correctlyAnswered;
    private int quizQuestionIndex;
    private int currentTextIndex = 0; // Tracks current TextObject index

    /**
     * Constructor of a Game
     * 
     * @param languageUUID
     * @param gameTitle
     * @param difficulty
     * @param uuid
     * @param category
     * @param info
     * @param textObjects
     * @param questions
     */
    public Game(UUID languageUUID, String gameTitle, Difficulty difficulty, UUID uuid, GameCategory category, GameInfo info, ArrayList<TextObject> textObjects, ArrayList<Question> questions) {
        this.languageUUID = languageUUID;
        this.gameTitle = gameTitle;
        this.difficulty = difficulty;
        this.uuid = uuid;
        this.category = category;
        this.info = info;
        this.textObjects = textObjects;
        this.questions = questions;
        correctlyAnswered = 0;
        quizQuestionIndex = 0;
        quizQuestions = new ArrayList<>();
    }

    /**
     * Introduces the game by providing the game info (description, objective, instructions, etc.)
     * @return A string containing the game introduction.
     */
    public String getIntroduction() {
        return info.toString();
    }

     /**
     * Retrieves the current TextObject to present to the user.
     * @return The current TextObject.
     */
    public TextObject getCurrentTextObject() {
        if (textObjects.isEmpty()) return null;
        return textObjects.get(currentTextIndex);
    }

    /**
     * Advances to the next TextObject
     * @return The next TextObject.
     */
    // Retrieve the next TextObject based on the current index without looping back to the beginning
    public TextObject getNextTextObject() {
        if (textObjects.isEmpty()) return null;

        // Check if we are at the last TextObject
        if (currentTextIndex < textObjects.size() - 1) {
            currentTextIndex++;
        } else {
            currentTextIndex = 0;
            return null;
        }
        
        // Return the current TextObject, which will either be the next one or the last one if we've reached the end
        return textObjects.get(currentTextIndex);
    }

    /**
     * Goes back to the previous TextObject
     * @return The previous TextObject.
     */
    public TextObject getPreviousTextObject() {
        if (textObjects.isEmpty()) return null;

        // Check if we are at the first TextObject
        if (currentTextIndex > 0) {
            currentTextIndex--;
        } else {
            return null;
        }

        // Return the current TextObject, which will either be the previous one or the first one if we've reached the start
        return textObjects.get(currentTextIndex);
    }

    public int getCurrentTextObjectIndex() {
        return currentTextIndex;
    }

    public int getMaxTextObjectIndex() {
        return textObjects.size() - 1;
    }

    public void setTextObjectIndex(int newIndex) {
        if(newIndex >= 0 && newIndex <= getMaxTextObjectIndex()) {
            currentTextIndex = newIndex;
        }
    }

    // Method to add a Question to the Game
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // Method to retrieve all Questions in the Game
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public int getNumberOfQuestions() {
        return quizQuestions.size() - 1;
    }

    public void answeredQuestionCorrectly() {
        correctlyAnswered++;
    }

    public Question getQuestion(int index) {
        return (index >= 0 && index < questions.size()) ? questions.get(index) : null;
    }

    // Retrieve a specific Question by UUID
    public Question getQuestion(UUID questionUUID) {
        for (Question question : questions) {
            if (question.getUUID().equals(questionUUID)) {
                return question;
            }
        }
        return null;
    }

    // Retrieve all Question UUIDs
    public ArrayList<UUID> getQuestionUUIDs() {
        ArrayList<UUID> questionUUIDs = new ArrayList<>();
        for (Question question : questions) {
            questionUUIDs.add(question.getUUID());
        }
        return questionUUIDs;
    }

    // Getter for TextObjects
    public ArrayList<TextObject> getTextObjects() {
        return textObjects.isEmpty() ? null : textObjects;
    }

    // Retrieve a specific TextObject by UUID
    public TextObject getTextObject(UUID textObjectUUID) {
        return textObjects.stream().filter(t -> t.getUUID().equals(textObjectUUID)).findFirst().orElse(null);
    }

    // Retrieve a random TextObject
    public TextObject getRandomTextObject() {
        if (textObjects.isEmpty()) return null;
        Random random = new Random();
        return textObjects.get(random.nextInt(textObjects.size()));
    }

    // Retrieve the next TextObject based on a given UUID
    public TextObject getNextTextObject(UUID textObjectUUID) {
        int index = -1;
        for (int i = 0; i < textObjects.size(); i++) {
            if (textObjects.get(i).getUUID().equals(textObjectUUID)) {
                index = i;
                break;
            }
        }
        return index == -1 ? null : textObjects.get((index + 1) % textObjects.size());
    }

    // Getters for other attributes
    public UUID getLanguageUUID() { return languageUUID; }
    public String getGameTitle() { return gameTitle; }
    public Difficulty getDifficulty() { return difficulty; }
    public UUID getUUID() { return uuid; }
    public GameInfo getInfo() { return info; }
    public GameCategory getCategory() { return category; }

    // toString method for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Game Details ===\n")
          .append("Title: ").append(gameTitle).append("\n")
          .append("Language UUID: ").append(languageUUID).append("\n")
          .append("Difficulty: ").append(difficulty).append("\n")
          .append("Game UUID: ").append(uuid).append("\n")
          .append("Game Category: ").append(category).append("\n")
          .append("Info: ").append(info != null ? info.toString() : "No Info Available").append("\n")
          .append("TextObjects: ").append(textObjects.isEmpty() ? "No TextObjects" : textObjects.size()).append("\n")
          .append("Questions: ").append(questions.isEmpty() ? "No Questions" : questions.size()).append("\n");
        return sb.toString();
    }

    // Pulls a predetermined set of questions based on specified requirements
    public void pullQuestionsForQuiz() {
        quizQuestions.clear();

        // Generate a random starting index for textObjects
        int startIndex = new Random().nextInt(textObjects.size());
        int multiChoiceStartIndex = new Random().nextInt(questions.size());

        // Pull 3 Multiple Choice Questions
        addQuestionsByType(QuestionType.MULTIPLE_CHOICE, 3, multiChoiceStartIndex);

        // Pull 1 Sequencing Question if the game is eligible for sequencing (story games for example)
        if (category == GameCategory.STORY) { // Not yet implemented
            // addQuestionsByType(QuestionType.SEQUENCING, 1, startIndex);
        } else if(category == GameCategory.WORD) {
            // Pull 2 Fill in the Blank Questions
            addQuestionsByType(QuestionType.FITB, 2, startIndex);

            // Pull 1 Matching Question
            addQuestionsByType(QuestionType.MATCHING, 1, startIndex);
        }
    }

    // Helper method to add a specific number of questions of a given type, starting from a given index
    public void addQuestionsByType(QuestionType type, int count, int index) {
        int added = 0;

        while (added < count) {
            Question question;
            if(type != QuestionType.MULTIPLE_CHOICE) {
                // if(type == QuestionType.FITB) {

                // }
                UUID questionUUID = textObjects.get(index).getUUID();
                question = QuestionFactory.createQuestion(type, questionUUID);


                // Move to the next index, wrapping around to 0 if we reach the end of textObjects
                index = (index + 1) % textObjects.size();
            } else {
                question = getQuestion(index);
                // Move to the next index, wrapping around to 0 if we reach the end of questions list
                index = (index + 1) % questions.size();
            }
            quizQuestions.add(question);
            added++;
        }
    }

    public Question startQuiz() {
        pullQuestionsForQuiz();
        quizQuestionIndex = 0;
        correctlyAnswered = 0;
        // System.out.println(getQuestions()); // for debug 
        // System.out.println(getQuizQuestions()); //for debug
        return getQuizQuestion();
    }

    public ArrayList<Question> getQuizQuestions() {
        return quizQuestions;
    }

    public Question getQuizQuestion() {
        return getQuizQuestion(quizQuestionIndex);
    }

    public Question getQuizQuestion(int index) {
        if (index >= 0 && index < quizQuestions.size()) {
            return quizQuestions.get(index);
        }
        return null;
    }

    public Question getNextQuizQuestion() {
        quizQuestionIndex++;
        if (quizQuestionIndex >= 0 && quizQuestionIndex < quizQuestions.size()) {
            return quizQuestions.get(quizQuestionIndex);
        }
        return null;
    }

    public int getNumberOfQuizQuestions() {
        return quizQuestions.size();
    }

    public int getNumberOfQuizQuestionAnsweredCorrectly() {
        return correctlyAnswered;
    }

    public boolean validateQuizAnswer(String answer) {
        Question question = getQuizQuestion();
        if (question != null && question.validateAnswer(answer)) {
            answeredQuestionCorrectly();
            return true;
        }
        return false;
    }
   

}
