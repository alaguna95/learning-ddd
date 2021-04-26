package org.alaguna.learningddd2.input_data.question_form.domain;

public interface QuestionFormRepository {

        QuestionForm getQuestionFormByType(String type);

}
