package com.learner.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

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
    public void goToMain(ActionEvent event) throws IOException, URISyntaxException {
        InputStream is = getClass().getResourceAsStream("/com/learner/game/profile_picture.png");
        File testFile = new File(is.toString());
        FileOutputStream out = new FileOutputStream(testFile);
        copyStream(is, out);
        System.out.println(testFile.toString());
        Image defaultPicture = new Image(testFile.toURI().toString());
        ImageModel.setCurrentImage(defaultPicture);
        App.setRoot("main");
    }

    public static void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

}
