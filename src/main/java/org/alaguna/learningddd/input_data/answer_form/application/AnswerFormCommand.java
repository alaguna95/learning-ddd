package org.alaguna.learningddd.input_data.answer_form.application;

import java.util.List;

public class AnswerFormCommand {

    private String formType;
    private String trainingId;
    private String questionId;
    private List<AnswerCommand> answers;

    public String getFormType() {
        return formType;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public List<AnswerCommand> getAnswers() {
        return answers;
    }
}
