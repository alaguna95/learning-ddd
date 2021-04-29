package org.alaguna.learningddd.input_data.question_form.domain;

public class Question {

    private QuestionId id;
    private QuestionName name;
    private QuestionSentence sentence;

    public Question(QuestionId id, QuestionName name, QuestionSentence sentence) {
        this.id = id;
        this.name = name;
        this.sentence = sentence;
    }

    public QuestionId getId() {
        return id;
    }

    public QuestionName getName() {
        return name;
    }

    public QuestionSentence getSentence() {
        return sentence;
    }
}
