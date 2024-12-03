package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SettingsController {
    private FileChooser fileChooser = new FileChooser();
    @FXML
    private TextField UserBox;

    @FXML
    private Button backButton;

    @FXML
    private TextField displaybox;

    @FXML
    private TextField emailBox;

    @FXML
    private Button fileSelectButton;

    @FXML
    private TextField passwordBox;

    @FXML
    public void selectPicture(ActionEvent event) {
        Stage stage = new Stage();
        fileChooser.setTitle("Select a new profile picture");
        fileChooser.showOpenDialog(stage);
    }

    @FXML
    public void goToMain (ActionEvent event) throws IOException {
        App.setRoot("main");
    }

}
