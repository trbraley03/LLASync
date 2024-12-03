package com.learner.model.innerdata;

/**
 * Enum to represent different game categories
 */
public enum GameCategory {

    WORD("Word Wonders", "Build Language Foundation"), 
    ALPHABET("Letter Launch", "Explore the Alphabet"),
    CULTURE("Cultural Connect", "Master Real-Life Situations"),
    STORY("Stories", "Improve Reading Proficiency");

    private final String categoryName;
    private final String description;

    /**
     * Constructor for GameCategory
     * @param categoryName
     * @param description
     */
    GameCategory(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    // Getter for the enum instance of the category
    public GameCategory getGameCategory() {
        return this;
    }

    // Getter for the category name
    public String getCategory() {
        return categoryName;
    }

    // Getter for the description
    public String getDescription() {
        return description;
    }

}
