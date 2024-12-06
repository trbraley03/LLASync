package com.learner.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import com.learner.game.App;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SettingsController implements Initializable{
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
    private ImageView profilePicture;

    @FXML
    public void selectPicture(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        fileChooser.setTitle("Select a new profile picture");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png") 
        );
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            InputStream is = getClass().getResourceAsStream("/com/learner/game/profile_picture.png");
            Path tempFile = Files.createTempFile("profile_picture", "png");
            Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
            Path destination = tempFile.toAbsolutePath();
            try {
                Files.copy(selectedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                Path source = destination;
                Files.move(source, source.resolveSibling("profile_picture.png"), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image saved successfully");
                backgroundTask();
                new Thread(() -> {
                    Image image = new Image(selectedFile.toURI().toString());

                    Platform.runLater(() -> {
                        profilePicture.setImage(image);
                        ImageModel.setCurrentImage(image);
                    });
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    @FXML
    public void goToMain (ActionEvent event) throws IOException {
        App.setRoot("main");
    }

    private void backgroundTask() {
        new Thread(() -> {
            try {
                Thread.sleep(1);
                Platform.runLater(() -> {
                    profilePicture.setImage(profilePicture.getImage());
                }
                );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profilePicture.setImage(ImageModel.getCurrentImage());
    }

}
