package com.languageLearner.questions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import org.junit.Test;

import com.learner.model.questions.QuestionType;
import com.learner.model.questions.SequencingQuestion;

import java.util.UUID;

public class SequencingQuestionTest {

    private SequencingQuestion question;

    @Test
    public void setUp() {
        UUID questionUUID = UUID.fromString("e4e1d515-7baf-4569-8c14-7c663b6e49f5");
        question = new SequencingQuestion(questionUUID);
        
        // Sample question data
        question.setQuestionText("Arrange the colors in the correct order: red, green, blue.");
        question.setChoices(new String[] {"red", "green", "blue"});
        question.setCorrectSequence(new String[] {"red", "green", "blue"});
    }

    @Test
    public void testGenerateQuestion() {
        question.generateQuestion();
        assertNotNull(question.getQuestionText(), "Question text should be generated.");
        assertEquals("Arrange the colors in the correct order: red, green, blue.", question.getQuestionText());
    }

    @Test
    public void testValidateCorrectAnswer() {
        String userAnswer = "red, green, blue";
        assertTrue(question.validateAnswer(userAnswer));
    }

    @Test
    public void testValidateIncorrectAnswer() {
        String userAnswer = "green, blue, red";
        assertFalse(question.validateAnswer(userAnswer));
    }

    @Test
    public void testCorrectSequence() {
        // Check the correct sequence validation
        String[] userSequence = {"red", "green", "blue"};
        assertTrue(question.validateSequence(userSequence));
    }

    @Test
    public void testIncorrectSequence() {
        // Check incorrect sequence validation
        String[] userSequence = {"blue", "red", "green"};
        assertFalse(question.validateSequence(userSequence));
    }
}
