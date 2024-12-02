package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SignUpController {

    @FXML
    private Button backButton;

    @FXML
    private Button signupButton;

    @FXML
    public void goToHome(ActionEvent event) throws IOException{
        App.setRoot("home");
    }

    @FXML
    public void submitSignup(ActionEvent event) throws IOException{
        //String email = emailBox.getText(); 
        //String username = usernameBox.getText(); 
        //String displayName = displayNameBox.getText(); 
        //String password = passwordBox.getText(); 
        //Facade facade = Facade.getInstance();
        //facade.registerUser(email, username, displayName, password); 

        App.setRoot("home");
    }
}
