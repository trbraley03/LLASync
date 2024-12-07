package com.learner.model.innerdata;

import java.util.UUID;

import org.json.simple.JSONObject;

import com.learner.model.Facade;

/**
 * TextObject <-- Game <--- Difficulty & Language
 */
public class TextObject {
    
    private final String text;
    private final String englishText;
    private final String linkedText;
    private final String englishLinkedText;
    private final String helperText;
    private final UUID uuid;
    private final UUID gameUUID;

    /**
     * 
     * @param text
     * @param englishText
     * @param linkedText
     * @param englishLinkedText
     * @param helperText
     * @param uuid
     * @param gameUUID
     */
    public TextObject(String text, String englishText, String linkedText, String englishLinkedText, String helperText, UUID uuid, UUID gameUUID) {
        this.text = text;
        this.englishText = englishText;
        this.linkedText = linkedText;
        this.englishLinkedText = englishLinkedText;
        this.helperText = helperText;
        this.uuid = uuid;
        this.gameUUID = gameUUID;
    }

    /**
     * For stories
     * @param text
     * @param englishText
     * @param uuid
     * @param gameUUID
     */
    public TextObject(String text, String englishText, UUID uuid, UUID gameUUID) {
        this.text = text;
        this.englishText = englishText;
        this.linkedText = "";
        this.englishLinkedText = "";
        this.helperText = "";
        this.uuid = uuid;
        this.gameUUID = gameUUID;
    }

    // UUID and GameUUID getters
    public UUID getUUID() {
        return uuid;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }

    // Standard getters
    public String getText() {
        return text;
    }

    public String getEnglishText() {
        return englishText;
    }

    public String getLinkedText() {
        return linkedText;
    }

    public String getEnglishLinkedText() {
        return englishLinkedText;
    }

    public String getHelperText() {
        return helperText;
    }

    public static TextObject fromJson(JSONObject textJson, UUID gameUUID) {
        String text = (String) textJson.get("text");
        String englishText = (String) textJson.get("englishText");
        String linkedText = (String) textJson.get("linkedText");
        String englishLinkedText = (String) textJson.get("englishLinkedText");
        String helperText = (String) textJson.get("helperText");
        UUID uuid = UUID.fromString((String) textJson.get("UUID"));

        TextObject textObject = new TextObject(text, englishText, linkedText, englishLinkedText, helperText, uuid, gameUUID);
        return textObject;
    }

    @Override
    public String toString() {
        return String.format(Facade.getInstance().getCurrentLanguage().getLanguageName() + ": %s\nEnglish: %s\nExample: %s\nHelper: %s",
            this.getText(),
            this.getEnglishText(),
            this.getLinkedText(),
            this.getHelperText());
    }

    public String toString(boolean translate) {
        if(translate) { 
            return this.getText();
        } 
        return this.getEnglishText();
    }

}
