package org.alaguna.input_data.answer_form.application;

import java.util.List;

public class AnswerFormCommand {

    private String id;
    private String formType;
    private String trainingId;
    private String questionFormId;
    private List<AnswerCommand> answers;

    public String getId() {
        return id;
    }
    public String getFormType() {
        return formType;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public String getQuestionFormId() {
        return questionFormId;
    }

    public List<AnswerCommand> getAnswers() {
        return answers;
    }
}
