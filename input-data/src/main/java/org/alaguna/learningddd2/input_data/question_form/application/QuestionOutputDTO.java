package org.alaguna.learningddd2.input_data.question_form.application;

import java.io.Serializable;

public class QuestionOutputDTO implements Serializable {

    private String id;
    private String name;
    private String sentence;

    public QuestionOutputDTO(String id, String name, String sentence) {
        this.id = id;
        this.name = name;
        this.sentence = sentence;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSentence() {
        return sentence;
    }
}
