package com.learner.model;

/**
 * Temp class for testing
 */
public class ADataLoadCheck {

    public static void main(String[] args) {
        // System.out.println("test");
        // System.out.println(Difficulty.EASY);
        // System.out.println(GameCategory.WORD);
        // System.out.println(GameCategory.STORY);
        // System.out.println(GameCategory.ALPHABET);
        Facade.getInstance().loadData();
        System.out.println(Facade.getInstance().getAvailableLanguages());
    }
}
