package org.alaguna.learningddd.input_data.answer_form.application;

public class AnswerCommand {

    private String id;
    private String questionId;
    private Integer value;

    public String getId() {
        return id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public Integer getValue() {
        return value;
    }
}
