package com.learner.controllers;

import java.io.IOException;

import com.learner.narration.Narrator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private void helloWorldButton() throws IOException {
        Narrator.playSound("Kumusta mundo!");
    }
}