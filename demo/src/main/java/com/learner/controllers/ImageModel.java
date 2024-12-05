package com.learner.controllers;

import javafx.scene.image.Image;

public class ImageModel {
    private static Image currentImage;

    public static Image getCurrentImage() {
        return currentImage;
    }

    public static void setCurrentImage(Image image) {
        currentImage = image;
    }
}
