package org.alaguna.input_data.answer_form.domain;

import org.alaguna.input_data.question_form.domain.QuestionId;

public class Answer {

    private AnswerId id;
    private QuestionId questionId;
    private AnswerValue value;

    public Answer(AnswerId id, QuestionId questionId, AnswerValue value) {
        this.id = id;
        this.questionId = questionId;
        this.value = value;
    }

    public AnswerId getId() {
        return id;
    }

    public QuestionId getQuestionId() {
        return questionId;
    }

    public AnswerValue getValue() {
        return value;
    }
}
