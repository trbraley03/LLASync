package com.learner.model;

import java.util.ArrayList;
import java.util.UUID;

import com.learner.model.loadwrite.DataConstants;
import com.learner.model.loadwrite.DataLoader;
import com.learner.model.questions.Question;

public class Facade {

    private static Facade instance;
    private final FacadeForGame gameFacade;
    private final GameManager gameManager = GameManager.getInstance();
    private final UserList userList = UserList.getInstance();

    private Language currentLanguage;
    private Difficulty currentDifficulty;
    private Game currentGame;

    private User currentUser;

    private Facade() {
        gameFacade = new FacadeForGame();
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
            System.out.println("success");
            return true;
        }
        System.out.println("failed");
        return false;
    }

    /**
     * If string returns as "true", then the user registered successfully,
     * otherwise the issues with registration are returned as a string
     */
    public String registerUser(String email, String username, String displayName, String password) {
        return userList.registerUser(email, username, displayName, password);
    }

    public void logoutUser() {
        // DataWriter.writeUserData(DataConstants.USER_FILE);
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

    /*
     * Set current language by UUID
     */
    public void selectLanguage(UUID langUUID) {
        currentLanguage = gameManager.getLanguageByUUID(langUUID);
    }

    /*
     * Set current language by UUID
     */
    public void selectLanguage(String lang) {
        currentLanguage = gameManager.findLanguage(lang);
    }

    /**
     * Set current difficulty by UUID
     * 
     * @param difficulty
     * @return
     */
    public void selectDifficulty(Difficulty difficulty) {
        currentDifficulty = difficulty;
    }

    public void selectDifficulty(String difficulty) {
        currentDifficulty = gameManager.findDifficulty(difficulty);
    }

    // Game-related methods delegated to GameFacade

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

    public boolean selectGame(UUID gameUUID) {
        if(currentDifficulty == null || currentLanguage == null) {
            return false;
        }
        currentGame = gameManager.findGameByUUID(gameUUID);
        gameFacade.selectGame(currentGame);
        return true;
    }

    // Navigation and quiz methods
    // Uses gameFacade
    
    public String showCurrentTextObject() {
        return gameFacade.showCurrentTextObject();
    }

    public String getNextTextObject() {
        return gameFacade.getNextTextObject();
    }

    public String getPreviousTextObject() {
        return gameFacade.getPreviousTextObject();
    }

    public Question startQuiz() {
        return gameFacade.startQuiz();
    }

    public Question getQuizQuestion() {
        return gameFacade.getQuizQuestion();
    }

    public Question getNextQuizQuestion() {
        return gameFacade.getNextQuizQuestion();
    }

    public boolean validateQuizAnswer(String answer) {
        return gameFacade.validateQuizAnswer(answer);
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

    public int getCurrentTextObjectIndex() {
        return gameFacade.getCurrentTextObjectIndex();
    }

    public int getMaxTextObjectIndex() {
        return gameFacade.getMaxTextObjectIndex();
    }

    // public int getCurrentQuizIndex() {
    //     return gameFacade.getCurrentQuestionIndex();
    // }

    public void setTextObjectIndex(int newIndex) {
        gameFacade.setTextObjectIndex(newIndex);
    }

    public String endGameSession() {
        if (currentUser == null || currentLanguage == null) return "No active game session.";
        return gameFacade.endGameSession(currentUser, currentLanguage.getUUID());
    }

}
