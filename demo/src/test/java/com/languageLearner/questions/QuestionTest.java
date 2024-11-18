package com.languageLearner.questions;
import static org.junit.Assert.*;

import org.junit.Test;

import com.learner.model.questions.FITBQuestion;

import java.util.UUID;

public class QuestionTest {

    private FITBQuestion question; // or whatever specific question type you're testing

    @Test
    public void setUp() {
        UUID uuid = UUID.randomUUID();
        question = new FITBQuestion(uuid); // Assuming this constructor exists
    }

    @Test
    public void testGetUUID() {
        assertNotNull(question.getUUID());
        assertEquals(question.getUUID().toString(), question.getUUID().toString()); // Example assertion
    }

    // Add more test methods here
}
