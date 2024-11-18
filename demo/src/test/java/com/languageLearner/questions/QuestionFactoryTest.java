package com.languageLearner.questions;

import static org.junit.Assert.*;

import org.junit.Test;

import com.learner.model.questions.FITBQuestion;
import com.learner.model.questions.MultipleChoiceQuestion;
import com.learner.model.questions.Question;
import com.learner.model.questions.QuestionFactory;
import com.learner.model.questions.QuestionType;

import java.util.UUID;

import org.junit.Before;

public class QuestionFactoryTest {
    
    private QuestionFactory questionFactory;

    @Before
    public void setUp() {
        questionFactory = new QuestionFactory();
    }

    @Test
    public void testCreateFITBQuestionReturnsCorrectInstance() {
        UUID uuid = UUID.randomUUID();
        Question question = questionFactory.createQuestion(QuestionType.FITB, uuid);
        assertTrue(question instanceof FITBQuestion);
    }

    @Test
    public void testCreateMultipleChoiceQuestionReturnsCorrectInstance() {
        UUID uuid = UUID.randomUUID();
        Question question = questionFactory.createQuestion(QuestionType.MULTIPLE_CHOICE, uuid);
        assertTrue(question instanceof MultipleChoiceQuestion);
    }
}
