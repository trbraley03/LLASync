package com.languageLearner.app;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.learner.model.Difficulty;
import com.learner.model.Facade;
import com.learner.model.GameManager;
import com.learner.model.UserList;
import com.learner.model.loadwrite.DataConstants;

public class FacadeWithDataLoadingTest {

    private Facade facade;

    @Test
    public void setUp() {
        // Load data using the DataLoader with specified paths
        GameManager.getInstance().clearData();
        UserList.getInstance().clearUsers();
        
        facade = Facade.getInstance();
        facade.loadData(DataConstants.GAME_DATA_FILE_JUNIT, DataConstants.USER_FILE_JUNIT);
    }

    @Test
    public void testLoginAndLogoutFlow() {
        // Attempt to login with a known user from the data
        String loginResult = facade.loginUser("sdindl@email.com", "Password2024!");
        assertEquals("Login successful.", loginResult);

        // Verify that the user is logged in
        assertTrue(facade.isUserLoggedIn());

        // Logout and verify
        String logoutResult = facade.logoutUser();
        assertEquals("Logout successful.", logoutResult);
        assertFalse(facade.isUserLoggedIn());
    }

    @Test
    public void testLanguageSelection() {
        // Fetch available languages after loading data
        ArrayList<String> languages = facade.getAvailableLanguages();
        assertNotNull(languages);
        assertFalse(languages.isEmpty());

        // Select the first language
        String selectionResult = facade.selectLanguage(0);
        assertTrue(selectionResult.startsWith("Language set to"));
    }

    @Test
    public void testGameSelectionAndNavigation() {
        // a language and difficulty should be set in the data
        facade.selectLanguage(0);
        facade.selectDifficulty(Difficulty.EASY);

        ArrayList<String> availableGames = facade.getAvailableGames();
        assertNotNull(availableGames);
        assertFalse(availableGames.isEmpty());

        // Select the first game
        String gameSelectionResult = facade.selectGame(0);
        assertTrue(gameSelectionResult.startsWith("Game '"));

        // Test navigation through text objects
        String textObject = facade.showCurrentTextObject();
        assertNotNull(textObject, "Initial text object should be available.");

        String nextTextObject = facade.nextTextObject();
        assertNotNull(nextTextObject, "Next text object should be available.");

        String prevTextObject = facade.previousTextObject();
        assertEquals(textObject, prevTextObject, "Previous text object should match the initial one.");
    }

    @Test
    public void testQuizFlow() {
        facade.selectLanguage(0);
        facade.selectDifficulty(Difficulty.EASY);
        facade.selectGame(0);

        String startQuizResult = facade.startQuiz();
        assertEquals("Quiz started. Answer the following questions.", startQuizResult);

        String question = facade.getNextQuizQuestion();
        assertNotNull(question, "First quiz question should be available.");

        // Simulate an answer (you can modify this based on your expected question types)
        boolean answerResult = facade.validateQuizAnswer("someAnswer");
        assertFalse(answerResult);
    }
}
