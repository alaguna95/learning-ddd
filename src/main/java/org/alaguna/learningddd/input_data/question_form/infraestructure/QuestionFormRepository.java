package org.alaguna.learningddd.input_data.question_form.infraestructure;

import org.alaguna.learningddd.input_data.question_form.domain.QuestionForm;

public interface QuestionFormRepository {

        QuestionForm getQuestionFormByType(String type);



}
