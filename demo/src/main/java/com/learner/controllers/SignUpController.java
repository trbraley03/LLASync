package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;
import com.learner.model.Facade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignUpController {

    private final Facade facade = Facade.getInstance();

    @FXML
    private TextField displayNameBox;

    @FXML
    private TextField emailBox;

    @FXML
    private TextField passwordBox;

    @FXML
    private TextField usernameBox;

    @FXML
    private Button signupButton;

    @FXML
    private Button backButton;

    @FXML
    public void goToHome(ActionEvent event) throws IOException{
        App.setRoot("home");
    }

    @FXML
    public void submitSignup(ActionEvent event) throws IOException{
        String email = emailBox.getText(); 
        String username = usernameBox.getText(); 
        String displayName = displayNameBox.getText(); 
        String password = passwordBox.getText(); 
        String registered = facade.registerUser(email, username, displayName, password); 

        if(email.isEmpty() || username.isEmpty() || displayName.isEmpty() || password.isEmpty()) {
            registered = "Please fill in all fields";
        }
        if(registered.equals("true")) {
            // This is a popup and can be removed for better ui alterative 
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Signup was successful!");
            alert.showAndWait();
            App.setRoot("home");
        } else {
            // Also a popup
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Failed");
            alert.setHeaderText(null);
            alert.setContentText(registered);
            alert.showAndWait();
        }
    }
}
