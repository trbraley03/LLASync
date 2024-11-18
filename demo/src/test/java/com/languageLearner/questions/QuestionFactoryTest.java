package com.languageLearner.questions;

import static org.junit.Assert.*;

import org.junit.Test;

import com.learner.model.questions.FITBQuestion;
import com.learner.model.questions.MultipleChoiceQuestion;
import com.learner.model.questions.Question;
import com.learner.model.questions.QuestionFactory;
import com.learner.model.questions.QuestionType;

import java.util.UUID;

public class QuestionFactoryTest {
    
    private QuestionFactory questionFactory;

    @Test
    void setUp() {
        questionFactory = new QuestionFactory();
    }

    @Test
    void testCreateFITBQuestionReturnsCorrectInstance() {
        UUID uuid = UUID.randomUUID();
        Question question = questionFactory.createQuestion(QuestionType.FITB, uuid);
        assertTrue(question instanceof FITBQuestion);
    }

    @Test
    void testCreateMultipleChoiceQuestionReturnsCorrectInstance() {
        UUID uuid = UUID.randomUUID();
        Question question = questionFactory.createQuestion(QuestionType.MULTIPLE_CHOICE, uuid);
        assertTrue(question instanceof MultipleChoiceQuestion);
    }
}
