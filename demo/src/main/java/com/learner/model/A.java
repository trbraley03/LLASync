package com.learner.model;

import com.learner.model.innerdata.GameCategory;

public class A {

    public static void main(String[] args) {
        System.out.println("test");
        System.out.println(Difficulty.EASY);
        System.out.println(GameCategory.WORD);
        System.out.println(GameCategory.STORY);
        System.out.println(GameCategory.ALPHABET);
        Facade.getInstance().loadData();
    }
}
