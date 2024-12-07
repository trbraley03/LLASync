package com.learner.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.learner.game.App;
import com.learner.model.Difficulty;
import com.learner.model.Facade;
import com.learner.model.Language;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller for the game story content screen.
 */
public class GameStoryContentController implements Initializable {

    private final Facade facade = Facade.getInstance();
    private final Language currentLanguage = facade.getCurrentLanguage();
    private final Difficulty currentDifficulty = facade.getCurrentDifficulty();
    private String titleText = facade.getCurrentGame().getGameTitle();
    private boolean translated = false;

    @FXML
    private Label title;

    @FXML
    private Button backButton;

    @FXML
    private Label gameContentText;

    @FXML
    private Button nextButton;

    @FXML
    private ImageView storyImage;

    @FXML
    private Button translateButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(titleText);
        translated = false;
        gameContentText.setText(facade.getCurrentGame().getCurrentTextObject().getText());
        storyImage.setPreserveRatio(true);
        setStoryImage();
    }

    private int getPageIndex() {
        return facade.getCurrentTextObjectIndex();
    }

    @FXML
    private void goBackToPreviousGameScreen(ActionEvent event) throws IOException {
        if(facade.getCurrentTextObjectIndex() == 0) {
            App.setRoot("GameTitleScreen");
        } else {
            setNewScreen();
            gameContentText.setText(facade.getCurrentGame().getCurrentTextObject().getText());
        }
    }

    @FXML
    private void goToNextGameScreen(ActionEvent event) throws IOException {
        if(facade.getCurrentTextObjectIndex() == facade.getMaxTextObjectIndex()) {
            App.setRoot("GameOutroScreen");
        } else {
            setNewScreen();
            gameContentText.setText(facade.getCurrentGame().getCurrentTextObject().getText());
        }
    }

    private void setStoryImage() {
        String imagePath = "/com/learner/game/story-images/" + titleText + "/" + getPageIndex() + ".png";
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl != null) {
            // System.out.println("Image URL: " + imageUrl.toString());
            Image image = new Image(imageUrl.toString());
            storyImage.setImage(image);
            // System.out.println("Image set successfully.");
        } 
        // else {
        //     System.out.println("Image not found: " + imagePath);
        //     System.out.println("Game title: " + title);
        //     System.out.println("Page index: " + getPageIndex());
        // }
    }

    @FXML
    private void translateText(ActionEvent event) throws IOException  {
        if(translated) {
            gameContentText.setText(facade.getCurrentGame().getCurrentTextObject().getText());
            translated = false;
        } else {
            gameContentText.setText(facade.getCurrentGame().getCurrentTextObject().getEnglishText());
            translated = true;
        }
    }

    private void setNewScreen() {
        translated = false;
        setStoryImage();
        gameContentText.setText(facade.getCurrentGame().getCurrentTextObject().getText());
    }

}
