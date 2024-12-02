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

    //  /**
    //  * Find a GameCategory by its category name (case-insensitive).
    //  * @param category the category name to search for
    //  * @return the corresponding GameCategory, or null if not found
    //  */
    // public static GameCategory fromCategory(String category) {
    //     for (GameCategory gameCategory : values()) {
    //         if (gameCategory.getCategory().equalsIgnoreCase(category)) {
    //             return gameCategory;
    //         }
    //     }
    //     throw new IllegalArgumentException("Invalid game category: " + category);
    // }
}
