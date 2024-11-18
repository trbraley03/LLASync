package com.languageLearner.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.learner.model.loadwrite.DataLoader;
import com.learner.model.GameManager;
import com.learner.model.UserList;
import com.learner.model.loadwrite.DataConstants;

public class DataLoaderTest {

    @Test
    public void clear() {
        UserList.getInstance().clearUsers();  // You may need to add a clearUsers() method.
        GameManager.getInstance().clearData();
    }

    /**
     * Return true after clearing
     */
    @Test
    public void testUserListIsEmptyAfterClearing() {
        assertTrue(UserList.getInstance().getUsers().isEmpty());
    }

    @Test
    public void testLoadingGameData() {
        GameManager gameManager = GameManager.getInstance();
        DataLoader.loadGameData(DataConstants.GAME_DATA_FILE_JUNIT);
        DataLoader.loadUserData(DataConstants.USER_FILE_JUNIT);
        assertNotNull(gameManager.getAllGames());
        assertFalse(gameManager.getAllGames().isEmpty()); // Games should not be empty after loading in data, for some reason its appearing empty
    }

    @Test
    public void testLoadingUserData() {
        DataLoader.loadGameData(DataConstants.GAME_DATA_FILE_JUNIT);
        DataLoader.loadUserData(DataConstants.USER_FILE_JUNIT);
        assertNotNull(UserList.getInstance().getUsers());
        // assertFalse(UserList.getInstance().getUsers().isEmpty());
        GameManager gameManager = GameManager.getInstance();
        System.out.println(gameManager.toString());
    }

}
