package org.alaguna.input_data.question_form.domain;

public class QuestionFormType {

    private QuestionFormTypeEnum value;

    public QuestionFormType(String value) {
        this.value = QuestionFormTypeEnum.valueOf(value);
    }

    public QuestionFormTypeEnum getValue() {
        return value;
    }
}
