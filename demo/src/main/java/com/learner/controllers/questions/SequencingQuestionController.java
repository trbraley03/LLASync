package com.learner.controllers.questions;

import com.learner.model.Facade;
import com.learner.model.questions.SequencingQuestion;

public class SequencingQuestionController {

    private final Facade facade = Facade.getInstance();
    private SequencingQuestion currentQuestion = (SequencingQuestion) facade.getQuizQuestion();
    
}
