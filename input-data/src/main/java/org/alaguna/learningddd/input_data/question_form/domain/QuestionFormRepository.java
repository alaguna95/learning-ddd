package org.alaguna.learningddd.input_data.question_form.domain;

public interface QuestionFormRepository {

        QuestionForm getQuestionFormByType(String type);

}
