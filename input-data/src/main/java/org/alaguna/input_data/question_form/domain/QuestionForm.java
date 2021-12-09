package org.alaguna.input_data.question_form.domain;

import java.util.List;

public class QuestionForm {

    private QuestionFormId questionFormId;
    private QuestionFormType type;
    private QuestionFormActive active;
    private List<Question> questions;

    public QuestionForm(QuestionFormId questionFormId, QuestionFormType type, QuestionFormActive active, List<Question> questions) {
        this.questionFormId = questionFormId;
        this.type = type;
        this.active = active;
        this.questions = questions;
    }

    public QuestionFormId getQuestionFormId() {
        return questionFormId;
    }

    public List<Question> getQuestions() {
        return questions;
    }


}
