package com.learner.model.loadwrite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * 
 */
public class DataConstants {

    // File paths
    public final static String GAME_DATA_FILE = "application\\src\\main\\data\\gamesData.json";
    public final static String GAME_DATA_FILE_JUNIT = "application\\src\\test\\resources\\gamesData.json";

    public final static String USER_FILE = "application\\src\\main\\data\\users.json";
    public final static String USER_FILE_JUNIT = "application\\src\\test\\resources\\users.json";

    // Constants for GameData.json

    // MAIN JSON ARRAY HOLDING EVERYTHING
    public static final String LANGUAGES = "LANGUAGES";

    // MAIN JSON OBJS
    public static final String LANG = "LANG";
    public static final String UUID = "UUID";

    // ADDITIONAL JSON ARRAYS 
    public static final String GAMES = "GAMES";
    public static final String QUESTIONS = "QUESTIONS";
    public static final String TEXT = "TEXT";

    // GAME ATTRIBUTES
    public static final String GAME_TITLE = "GAME_TITLE";
    public static final String DIFFICULTY = "DIFFICULTY";
    public static final String CATEGORY = "CATEGORY";

    // INFO OBJ + ITS OBJS/ARRAYS
    public static final String INFO = "INFO";
    public static final String DESCRIPTION = "description";
    public static final String OBJECTIVE = "objective";
    public static final String INSTRUCTIONS = "instructions"; // JSON ARRAY (holds strings split by commas)
    public static final String PREP = "prep";                 // JSON ARRAY (holds 3 json objects)

    // JSON OBJS WITHIN PREP OBJECT
    public static final String INTRO_CONCEPT = "introConcept"; 
    public static final String EXAMPLE_USAGE = "exampleUsage";
    public static final String GAME_TIP = "gameTip";

    // JSON OBJS WITHIN TEXT ARRAY (additonally a uuid is held within
    public static final String TEXT_OBJ = "text";
    public static final String ENGLISH_TEXT = "englishText";
    public static final String LINKED_TEXT = "linkedText";
    public static final String ENGLISH_LINKED_TEXT = "englishLinkedText";
    public static final String HELPER_TEXT = "helperText";

    // WITHIN QUESTION
    public static final String QUESTION = "question";          // JSON OBJ
    public static final String CHOICES = "choices";            // JSON ARRAY (postion 0 should always be the correct answer)

    // Constants for User.json

    // UUID of games that can use sequencing questions
    public static final ArrayList<UUID> SEQUENCING_GAMES = new ArrayList<>(Arrays.asList(
           
    ));

    public static boolean isJUnitTest() {
        for(StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if(element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }

    
}
