package com.learner.controllers;

import java.io.IOException;

import com.learner.narration.Narrator;

import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Narrator.playSound("Kumusta mundo!");
    }
}
