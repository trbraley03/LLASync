module com.learner.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires json.simple;
    requires jlayer;
    requires software.amazon.awssdk.core;
    requires software.amazon.awssdk.services.polly;
    requires software.amazon.awssdk.regions;
    requires software.amazon.awssdk.utils;
    requires org.slf4j;
    requires org.slf4j.simple;
    requires software.amazon.awssdk.awscore;
    requires software.amazon.eventstream;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;
    requires gson;
    requires java.sql;

    opens com.learner.game to javafx.fxml;
    exports com.learner.game;

    opens com.learner.narration to java.fxml;
    exports com.learner.narration;

    opens com.learner.model to javafx.fxml;
    exports com.learner.model;

    opens com.learner.controllers to javafx.fxml;
    exports com.learner.controllers;

    opens com.learner.controllers.questions to javafx.fxml; 
    exports com.learner.controllers.questions to javafx.fxml; 
}
