package com.learner.controllers.questions;

import com.learner.model.Facade;
import com.learner.model.questions.SequencingQuestion;

/**
 * Unimplemented, simular to matching
 */
public class SequencingQuestionController {

    private final Facade facade = Facade.getInstance();
    private SequencingQuestion currentQuestion = (SequencingQuestion) facade.getQuizQuestion();
    
    
}
