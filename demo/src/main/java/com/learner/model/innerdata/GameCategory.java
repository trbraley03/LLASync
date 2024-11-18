package com.learner.model.innerdata;

/**
 * Enum to represent different game categories
 */
public enum GameCategory {

    WORD("Word Game"), 
    STORY("Story Game"), 
    ALPHABET("Alphabet Game");

    private final String category;

    /**
     * Constructor to initialize the display name
     * @param displayName
     */
    GameCategory(String category) {
        this.category = category;
    }

    // Getter for the string representation
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category;
    }
}
