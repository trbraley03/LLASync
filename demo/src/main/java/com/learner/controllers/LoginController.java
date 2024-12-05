package com.learner.controllers;

import java.io.File;
import java.io.IOException;

import com.learner.game.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class LoginController {

    @FXML
    private Button backButton;

    @FXML
    private TextField emailBox;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordBox;

    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    public void goToMain(ActionEvent event) throws IOException {
        File newFile = new File("C:/Users/dunca/Documents/Software Engineering Projects/LLASync/demo/src/main/resources/com/learner/game/profile_picture.png");
        Image defaultPicture = new Image(newFile.toURI().toString());
        ImageModel.setCurrentImage(defaultPicture);
        App.setRoot("main");
    }

}
