package com.learner.model.loadwrite;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.learner.model.User;
import com.learner.model.UserList;
import com.learner.model.questions.Question;

public class DataWriter {

    /**
     * Writes user data to a specified JSON file path
     * @param filePath the file path of the JSON file to save user data to
     */
    @SuppressWarnings({"unchecked", "CallToPrintStackTrace"})
    public static void writeUserData(String filePath) {
        UserList userList = UserList.getInstance();
        JSONArray usersArray = new JSONArray();

        for (User user : userList.getUsers()) {
            JSONObject userJson = new JSONObject();
            userJson.put("uuid", user.getUUID().toString());
            userJson.put("email", user.getEmail());
            userJson.put("username", user.getUsername());
            userJson.put("displayname", user.getDisplayName());
            userJson.put("password", user.getPassword());

            // Progress trackers
            JSONArray progressTrackersArray = new JSONArray();
            for (User.ProgressTracker tracker : user.getProgressTrackers()) {
                System.out.println(tracker);
                JSONObject trackerJson = new JSONObject();
                trackerJson.put("languageUUID", tracker.getUUID().toString());
                trackerJson.put("languageName", tracker.getLanguageName());

                // Completed games (store as UUIDs)
                JSONArray completedGamesArray = new JSONArray();
                for (UUID gameUUID : tracker.getCompletedGames()) {
                    JSONObject gameJson = new JSONObject();
                    gameJson.put("gameUUID", gameUUID.toString());
                    completedGamesArray.add(gameJson);
                }
                trackerJson.put("completedGames", completedGamesArray);

                // Missed questions
                JSONArray missedQuestionsArray = new JSONArray();
                for (Question question : tracker.getMissedQuestions()) {
                    JSONObject questionJson = new JSONObject();
                    questionJson.put("uuid", question.getUUID().toString());
                    questionJson.put("questionType", question.getQuestionType().name());
                    missedQuestionsArray.add(questionJson);
                }
                trackerJson.put("missedQuestions", missedQuestionsArray);

                progressTrackersArray.add(trackerJson);
            }
            userJson.put("progressTrackers", progressTrackersArray);

            usersArray.add(userJson);
        }

        // Create the final JSON object
        JSONObject finalJson = new JSONObject();
        finalJson.put("users", usersArray);

        // Write the JSON data to the file with pretty-printing
        try (FileWriter file = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJsonString = gson.toJson(finalJson);
            file.write(prettyJsonString);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}