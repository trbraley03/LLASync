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

    opens com.learner.game to javafx.fxml;
    exports com.learner.game;

    opens com.learner.narration to java.fxml;
    exports com.learner.narration;
}
