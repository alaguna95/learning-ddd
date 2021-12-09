package org.alaguna.input_data.question_form.application;

import java.io.Serializable;
import java.util.List;

public class QuestionFormOutputDTO implements Serializable {

    private String id;
    private List<QuestionOutputDTO> questions;

    public QuestionFormOutputDTO(String id,  List<QuestionOutputDTO> questions) {
        this.id = id;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public List<QuestionOutputDTO> getQuestions() {
        return questions;
    }
}
