package com.languageLearner.questions;


import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.learner.model.questions.QuestionType;

public class QuestionTypeTest {

    @Test
    public void testEnumValues() {
        QuestionType[] types = QuestionType.values();
        assertEquals(3, types.length);
        assertEquals(QuestionType.FITB, types[0]);
        assertEquals(QuestionType.MULTIPLE_CHOICE, types[1]);
    
    }
}
