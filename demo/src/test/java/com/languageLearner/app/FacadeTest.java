package com.languageLearner.app;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

import org.junit.Test;

import com.learner.model.Difficulty;
import com.learner.model.Facade;

public class FacadeTest {

    private Facade facade;

    @Before
    public void getInstanceAndLoad() {
        facade = Facade.getInstance();
        facade.loadData("path/to/gameData.json", "path/to/userData.json"); // Mock or temporary paths for testing
    }

    @Test
    public void testLoginUser() {
        facade.registerUser("test@example.com", "testUser", "Test Display", "test123");
        String result = facade.loginUser("test@example.com", "test123");
        assertEquals("Login successful.", result);
        assertTrue(facade.isUserLoggedIn());
    }

    @Test
    public void testLogoutUser() {
        facade.registerUser("test@example.com", "testUser", "Test Display", "test123");
        facade.loginUser("test@example.com", "test123");
        assertTrue(facade.isUserLoggedIn());

        String logoutMessage = facade.logoutUser();
        assertEquals("Logout successful.", logoutMessage);
        assertFalse(facade.isUserLoggedIn());
    }

    @Test
    public void testSelectLanguage() {
        facade.registerUser("user@example.com", "user", "User Display", "password");
        facade.loginUser("user@example.com", "password");

        String languageSelection = facade.selectLanguage(0); // Assuming the first language is valid
        assertTrue(languageSelection.startsWith("Language set to"));
    }

    @Test
    public void testSelectDifficulty() {
        facade.registerUser("user@example.com", "user", "User Display", "password");
        facade.loginUser("user@example.com", "password");
        facade.selectLanguage(0);

        String difficultySelection = facade.selectDifficulty(Difficulty.EASY);
        assertEquals("Difficulty set to EASY.", difficultySelection);
    }

    @Test
    public void testGetAvailableGames() {
        facade.registerUser("user@example.com", "user", "User Display", "password");
        facade.loginUser("user@example.com", "password");
        facade.selectLanguage(0);
        facade.selectDifficulty(Difficulty.EASY);

        ArrayList<String> games = facade.getAvailableGames();
        assertFalse(games.isEmpty());
    }

    @Test
    public void testSelectGame() {
        facade.registerUser("user@example.com", "user", "User Display", "password");
        facade.loginUser("user@example.com", "password");
        facade.selectLanguage(0);
        facade.selectDifficulty(Difficulty.EASY);

        String selectGameMessage = facade.selectGame(0); // Assuming the first game index is valid
        assertTrue(selectGameMessage.startsWith("Game '"));
    }
}
