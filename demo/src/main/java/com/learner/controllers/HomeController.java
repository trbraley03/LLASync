package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private Button loginButton;
        
    @FXML
    private Button signUpButton;

    @FXML
    private void goToLogin() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void goToSignup() throws IOException {
        //Narrator.playSound("Kumusta mundo!");
        App.setRoot("signup");
    }
}