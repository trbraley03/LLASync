package com.learner.model.loadwrite;

/**
 * 
 */
public class DataConstants {

    // File paths for data
    public final static String GAME_DATA_FILE = "demo\\src\\main\\resources\\com\\learner\\game\\gamesData.json";
    public final static String GAME_DATA_FILE_JUNIT = "demo\\src\\test\\resources\\gamesData.json";

    public final static String USER_FILE = "demo\\src\\main\\resources\\com\\learner\\game\\users.json";
    public final static String USER_FILE_JUNIT = "demo\\src\\test\\resources\\users.json"; 

    // Default profile picture path
    public final static String DEFAULT_PROFILE_PICTURE_PATH = "/com/learner/game/fxml-images/default-profile-picture.png";
    public final static String DEFAULT = "default"; // Default profile picture name in json file

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
    public static final String GAME = "GAME";
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

    // JSON OBJS WITHIN TEXT ARRAY 
    // has UUID = "uuid"
    public static final String TEXT_OBJ = "text";
    public static final String ENGLISH_TEXT = "englishText";
    public static final String LINKED_TEXT = "linkedText";
    public static final String ENGLISH_LINKED_TEXT = "englishLinkedText";
    public static final String HELPER_TEXT = "helperText";

    // WITHIN QUESTION
    public static final String QUESTION = "question";          // JSON OBJ
    public static final String CHOICES = "choices";            // JSON ARRAY (postion 0 should always be the correct answer, string values)

    // Constants for users.json
    // users is outers json array 
    public static final String USERS = "users";

    // Within users
    // has UUID = "uuid"
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String DISPLAYNAME = "displayname";
    public static final String PASSWORD = "password";
    public static final String PROFILE_PICTURE = "profilePicture";
    public static final String FEEDBACK_AUDIO_SETTING = "feedbackAudioSetting"; // boolean
    public static final String PROGRESS_TRACKERS = "progressTrackers"; // JSON ARRAY

    // ProgressTracker data constants for users.json
    public static final String LANGUAGE_NAME = "languageName";
    public static final String LANGUAGE_UUID =  "languageUUID";
    public static final String COMPLETED_GAMES = "completedGames"; // JSON ARRAY
    public static final String MISSED_QUESTIONS = "missedQuestions"; // JSON ARRAY

    // Constants for completedGames array
    public static final String GAME_UUID = "gameUUID";

    // Constants for missedQuestions array
    // UUID = "uuid"
    public static final String QUESTION_TYPE = "questionType";

    public static boolean isJUnitTest() {
        for(StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if(element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }

    
}
