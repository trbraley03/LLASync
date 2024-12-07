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
import javafx.scene.image.ImageView;

/**
 * Controller for the game story content screen.
 */
public class GameStoryContentController implements Initializable {

    private final Facade facade = Facade.getInstance();
    private final Language currentLanguage = facade.getCurrentLanguage();
    private final Difficulty currentDifficulty = facade.getCurrentDifficulty();
    private String title = facade.getCurrentGame().getGameTitle();
    private boolean translated = false;

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
            App.setRoot("GameIntroScreen");
        } else {
            facade.getPreviousTextObject();
            gameContentText.setText(facade.showCurrentTextObject());
        }
    }

    @FXML
    private void goToNextGameScreen(ActionEvent event) throws IOException {
        if(facade.getCurrentTextObjectIndex() == facade.getMaxTextObjectIndex()) {
            App.setRoot("GameOutroScreen");
        } else {
            facade.getNextTextObject();
            gameContentText.setText(facade.showCurrentTextObject());
        }
    }

    private void setStoryImage() {
        String imagePath = "demo/src/main/resources/com/learner/game/story-images/" + title + "/" + getPageIndex() + ".png";
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl != null) {
            storyImage = new ImageView(imageUrl.toString());
        }
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

}
