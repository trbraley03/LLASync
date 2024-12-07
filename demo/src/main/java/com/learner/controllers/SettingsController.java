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
import com.learner.model.Facade;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SettingsController implements Initializable{

    private Facade facade = Facade.getInstance();

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
    private Button updateProfilePicture;

    @FXML
    private TextField passwordBox;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Button updateDisplaynamebutton;

    @FXML
    private Button updateEmailButton;

    @FXML
    private Button updatePasswordButton;

    @FXML
    private Button updateUsernameButton;

    @FXML
    private void updateEmail(ActionEvent event) {
        if (!emailBox.getText().isEmpty()) {
            String newEmail = emailBox.getText();
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION, "Are you sure you would like to change your email to " + newEmail + "?", ButtonType.YES, ButtonType.CANCEL);
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean success = facade.changeEmail(newEmail).equals("Email changed successfully");
                    showResultAlert(success, "Email");
                }
            });
        }
    }

    @FXML
    private void updatePassword(ActionEvent event) {
        if (!passwordBox.getText().isEmpty()) {
            String newPassword = passwordBox.getText();
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION, "Are you sure you would like to change your password?", ButtonType.YES, ButtonType.CANCEL);
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean success = facade.changePassword(newPassword).equals("Password changed successfully");
                    showResultAlert(success, "Password");
                }
            });
        }
    }

    @FXML
    private void updateUsername(ActionEvent event) {
        if (!UserBox.getText().isEmpty()) {
            String newUsername = UserBox.getText();
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION, "Are you sure you would like to change your username to " + newUsername + "?", ButtonType.YES, ButtonType.CANCEL);
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean success = facade.changeUsername(newUsername).equals("Username changed successfully");
                    showResultAlert(success, "Username");
                }
            });
        }
    }

    @FXML
    private void updateDisplayname(ActionEvent event) {
        if (!displaybox.getText().isEmpty()) {
            String newDisplayName = displaybox.getText();
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION, "Are you sure you would like to change your display name to " + newDisplayName + "?", ButtonType.YES, ButtonType.CANCEL);
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean success = facade.changeDisplayName(newDisplayName).equals("Display Name changed successfully");
                    showResultAlert(success, "Display Name");
                }
            });
        }
    }

    private void showResultAlert(boolean success, String field) {
        Alert resultAlert = new Alert(success ? AlertType.INFORMATION : AlertType.ERROR);
        resultAlert.setTitle(success ? "Success" : "Failure");
        resultAlert.setHeaderText(null);
        resultAlert.setContentText(success ? field + " changed successfully!" : "Failed to change " + field + ".");
        resultAlert.showAndWait();
    }


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
