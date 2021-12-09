package org.alaguna.input_data.objects;

import org.alaguna.input_data.answer_form.application.AnswerCommand;

import java.util.List;

public class AnswerFormCreateCommandBuilder {

    private String id;
    private String formType;
    private String trainingId;
    private String questionFormId;
    private List<AnswerCommand> answers;

    public AnswerFormCreateCommandBuilder() {
        id = "5317b0cd-8ff5-49c6-b165-17149b023489";
        formType = "PRE_COMPETITION";
        trainingId = "a1ff75a1-4358-48ae-b035-34df807d99fc";
        questionFormId = "e61a2617-8be7-433c-8c77-798beba36845";
    }

}
