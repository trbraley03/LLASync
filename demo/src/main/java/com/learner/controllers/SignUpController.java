package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;
import com.learner.model.Facade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignUpController {

    @FXML
    private Button backButton;

    @FXML
    private Button enterButton;

    @FXML
    private TextField enterDisplayName;

    @FXML
    private TextField enterEmail;

    @FXML
    private PasswordField enterPassword;

    @FXML
    private TextField enterUsername;

    private final Facade facade = Facade.getInstance();

    @FXML
    void backPage(MouseEvent event) throws IOException {
        // back button action
        App.setRoot("primary");
    }

    @FXML
    void submitSignup(MouseEvent event) {
        String email = enterEmail.getText();
        String username = enterUsername.getText();
        String displayName = enterDisplayName.getText();
        String password = enterPassword.getText();

        String result = facade.registerUser(email, username, displayName, password);
        System.out.println(result);

    }

}
