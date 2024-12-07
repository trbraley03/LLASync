package com.learner.model;

import java.util.ArrayList;
import java.util.UUID;

import com.learner.model.innerdata.TextObject;
import com.learner.model.loadwrite.DataConstants;
import com.learner.model.loadwrite.DataLoader;
import com.learner.model.questions.Question;

public class Facade {

    private static Facade instance;
    private final GameManager gameManager = GameManager.getInstance();
    private final UserList userList = UserList.getInstance();

    private Language currentLanguage;
    private Difficulty currentDifficulty;
    private Game currentGame;
    private User currentUser;

    private Facade() {
        // Private constructor to enforce singleton pattern
    }

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public Language getCurrentLanguage() {
        return currentLanguage;
    }

    public Difficulty getCurrentDifficulty() {
        return currentDifficulty;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    // Data loading
    public void loadData(String gameDataFilePath, String userDataFilePath) {
        DataLoader.loadGameData(gameDataFilePath);
        DataLoader.loadUserData(userDataFilePath);
    }

    public void loadData() {
        DataLoader.loadGameData(DataConstants.GAME_DATA_FILE);
        DataLoader.loadUserData(DataConstants.USER_FILE);
    }

    // User session management
    public boolean loginUser(String email, String password) {
        User user = userList.login(email, password);
        if (user != null) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public String registerUser(String email, String username, String displayName, String password) {
        return userList.registerUser(email, username, displayName, password);
    }

    public String changeEmail(String email) {
        return currentUser.changeEmail(email);
    }

    public String changeUsername(String username) {
        return currentUser.changeUsername(username);
    }

    public String changeDisplayName(String displayName) {
        return currentUser.changeDisplayName(displayName);
    }

    public String changePassword(String password) {
        return currentUser.changePassword(password);
    }

    public void logoutUser() {
        currentUser = null;
        currentLanguage = null;
        currentDifficulty = null;
        currentGame = null;
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }

    public void quitApplication() {
        if (isUserLoggedIn()) {
            logoutUser();
        }
        System.exit(0);
    }

    // Language and difficulty selection
    public ArrayList<Language> getAvailableLanguages() {
        return gameManager.getAllLanguages();
    }

    public void selectLanguage(UUID langUUID) {
        currentLanguage = gameManager.getLanguageByUUID(langUUID);
    }

    public void selectLanguage(String lang) {
        currentLanguage = gameManager.findLanguage(lang);
    }

    public void selectDifficulty(Difficulty difficulty) {
        currentDifficulty = difficulty;
    }

    public void selectDifficulty(String difficulty) {
        currentDifficulty = gameManager.findDifficulty(difficulty);
    }

    // Game management
    public boolean selectGame(UUID gameUUID) {
        if (currentDifficulty == null || currentLanguage == null) {
            return false;
        }
        currentGame = gameManager.findGameByUUID(gameUUID);
        return true;
    }

    public int getCurrentTextObjectIndex() {
        if (currentGame == null) {
            throw new IllegalStateException("Current game is not set.");
        }
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

    private String formatTextObjectContent(TextObject textObject) {
        return String.format(currentLanguage.getLanguageName() + ": %s\nEnglish: %s\nExample: %s\nHelper: %s",
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

    public String endGameSession() {
        if (currentUser != null && currentGame != null) {
            currentUser.getProgressTracker(currentLanguage.getUUID()).addCompletedGame(currentGame.getUUID());
        }
        currentGame = null;
        return "Game session ended. Progress saved.";
    }

    // Progress tracking
    public void addGameToCompletedGames() {
        currentUser.addCompletedGame(currentLanguage.getUUID());
    }

    public void addMissedQuestion(Question question) {
        currentUser.addMissedQuestion(question);
    }

    public void removeMissedQuestion(Question question) {
        currentUser.removeMissedQuestion(question);
    }

    public int getTotalNumberOfCompletedGames() {
        return currentUser.getTotalNumberOfCompletedGames();
    }

    public int getNumberOfCompletedGamesForCurrentLanguage() {
        return currentUser.getNumberOfCompletedGames(currentLanguage.getUUID());
    }

    public ArrayList<Game> getAvailableGames() {
        return (currentLanguage != null && currentDifficulty != null) 
            ? getAvailableGames(currentLanguage.getUUID(), currentDifficulty) 
            : null;
    }

    private ArrayList<Game> getAvailableGames(UUID languageUUID, Difficulty difficulty) {
        ArrayList<Game> games = new ArrayList<>();
        for (Game game : gameManager.getGamesByDifficulty(languageUUID, difficulty)) {
            games.add(game);
        }
        return games;
    }

    public int getNumberOfQuizQuestions() {
        return currentGame.getNumberOfQuizQuestions();
    }

    public int getNumberOfQuestionsAnsweredCorrectly() {
        return currentGame.getNumberOfQuizQuestionAnsweredCorrectly();
    }

    public double getQuestionAverageResult() {
        return (double)getNumberOfQuestionsAnsweredCorrectly()/(double)getNumberOfQuizQuestions();
    }
}