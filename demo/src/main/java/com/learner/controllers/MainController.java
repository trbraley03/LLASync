package com.learner.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.learner.game.App;
import com.learner.model.Facade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainController implements Initializable{

    @FXML
    private Button logoutButton;

    @FXML
    private Button playButton;

    @FXML
    private ImageView profileButton;

    @FXML
    private Button progressButton;

    @FXML
    private Button settingsButton;

    @FXML
    public void goToPlay(ActionEvent event) throws IOException {
        App.setRoot("pickLanguage");
    }

    @FXML
    public void goToProgress(ActionEvent event) throws IOException {
        App.setRoot("progressTracker");
    }

    @FXML
    public void goToSettings(ActionEvent event) throws IOException {
        App.setRoot("settings");
    }

    @FXML
    public void logoutToHome(ActionEvent event) throws IOException {
        Facade.getInstance().saveUserData();
        App.setRoot("home");
    }

    @FXML
    public void makeOptionsVisible(MouseEvent event) throws IOException{
        logoutButton.setVisible(!logoutButton.isVisible());
        settingsButton.setVisible(!settingsButton.isVisible());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profileButton.setImage(ImageModel.getCurrentImage());;
    }

}
