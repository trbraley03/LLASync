package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SettingsController {

    @FXML
    private TextField UserBox;

    @FXML
    private Button backButton;

    @FXML
    private TextField displaybox;

    @FXML
    private TextField emailBox;

    @FXML
    private TextField passwordBox;

    @FXML
    public void goToMain (ActionEvent event) throws IOException {
        App.setRoot("main");
    }

}
