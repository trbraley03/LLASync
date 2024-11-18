package com.languageLearner.data;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.learner.model.loadwrite.DataConstants;

public class DataConstantsTest {

    @Test
    public void testGameDataFilePath() {
        String expectedGameDataFilePath = "demo\\src\\main\\resources\\com\\learner\\game\\gamesData.json"; 
        assertEquals("Mismatch in GAME_DATA_FILE path.", expectedGameDataFilePath, DataConstants.GAME_DATA_FILE);
    }

    @Test
    public void testGameDataFilePathJUnit() {
        String expectedJUnitFilePath = "demo\\src\\test\\resources\\gamesData.json";
        assertEquals("Mismatch in GAME_DATA_FILE_JUNIT path.", expectedJUnitFilePath, DataConstants.GAME_DATA_FILE_JUNIT);
    }

    @Test
    public void testUserFilePath() {
        String expectedUserFilePath = "demo\\src\\main\\resources\\com\\learner\\game\\users.json";
        assertEquals("Mismatch in USER_FILE path.", expectedUserFilePath, DataConstants.USER_FILE);
    }

    @Test
    public void testUserFilePathJUnit() {
        String expectedJUnitUserPath = "demo\\src\\test\\resources\\users.json";
        assertEquals("Mismatch in USER_FILE_JUNIT path.", expectedJUnitUserPath, DataConstants.USER_FILE_JUNIT);
    }

    @Test
    public void testMainJsonKeys() {
        assertEquals("Incorrect value for LANGUAGES key.", "LANGUAGES", DataConstants.LANGUAGES);
        assertEquals("Incorrect value for LANG key.", "LANG", DataConstants.LANG);
        assertEquals("Incorrect value for UUID key.", "UUID", DataConstants.UUID);
    }

    @Test
    public void testInfoKeys() {
        assertEquals("Incorrect value for INFO key.", "INFO", DataConstants.INFO);
        assertEquals("Incorrect value for DESCRIPTION key.", "description", DataConstants.DESCRIPTION);
        assertEquals("Incorrect value for OBJECTIVE key.", "objective", DataConstants.OBJECTIVE);
        assertEquals("Incorrect value for INSTRUCTIONS key.", "instructions", DataConstants.INSTRUCTIONS);
        assertEquals("Incorrect value for PREP key.", "prep", DataConstants.PREP);
    }

    @Test
    public void testPrepKeys() {
        assertEquals("Incorrect value for INTRO_CONCEPT key.", "introConcept", DataConstants.INTRO_CONCEPT);
        assertEquals("Incorrect value for EXAMPLE_USAGE key.", "exampleUsage", DataConstants.EXAMPLE_USAGE);
        assertEquals("Incorrect value for GAME_TIP key.", "gameTip", DataConstants.GAME_TIP);
    }

    @Test
    public void testGameArraysKeys() {
        assertEquals("Incorrect value for GAMES key.", "GAMES", DataConstants.GAMES);
        assertEquals("Incorrect value for QUESTIONS key.", "QUESTIONS", DataConstants.QUESTIONS);
        assertEquals("Incorrect value for TEXT key.", "TEXT", DataConstants.TEXT);
    }

    @Test
    public void testTextObjectKeys() {
        assertEquals("Incorrect value for TEXT_OBJ key.", "text", DataConstants.TEXT_OBJ);
        assertEquals("Incorrect value for ENGLISH_TEXT key.", "englishText", DataConstants.ENGLISH_TEXT);
        assertEquals("Incorrect value for LINKED_TEXT key.", "linkedText", DataConstants.LINKED_TEXT);
        assertEquals("Incorrect value for ENGLISH_LINKED_TEXT key.", "englishLinkedText", DataConstants.ENGLISH_LINKED_TEXT);
        assertEquals("Incorrect value for HELPER_TEXT key.", "helperText", DataConstants.HELPER_TEXT);
    }

    @Test
    public void testQuestionKeys() {
        assertEquals("Incorrect value for QUESTION_TEXT key.", "questionText", DataConstants.QUESTION_TEXT);
        assertEquals("Incorrect value for CHOICES key.", "choices", DataConstants.CHOICES);
    }

    @Test
    public void testSequencingGames() {
        ArrayList<UUID> sequencingGames = DataConstants.SEQUENCING_GAMES;
        assertNotNull("SEQUENCING_GAMES list should not be null.", sequencingGames);
        
        // Add specific UUIDs to test for if necessary, e.g., assertTrue(sequencingGames.contains(someExpectedUUID));
        assertTrue("SEQUENCING_GAMES list should be empty.", sequencingGames.isEmpty());
    }
}
