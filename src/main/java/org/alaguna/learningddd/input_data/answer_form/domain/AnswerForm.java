package org.alaguna.learningddd.input_data.answer_form.domain;

import org.alaguna.learningddd.input_data.question_form.domain.QuestionFormId;
import org.alaguna.learningddd.input_data.question_form.domain.QuestionFormType;
import org.alaguna.learningddd.input_data.training.domain.TrainingId;
import org.alaguna.learningddd.shared.domain.AggregateRoot;

import java.util.List;

public class AnswerForm extends AggregateRoot {

    private AnswerFormId id;
    private TrainingId trainingId;
    private QuestionFormId questionFormId;
    private QuestionFormType type;
    private List<Answer> answers;

    public AnswerForm(AnswerFormId id, TrainingId trainingId, QuestionFormId questionFormId, QuestionFormType type,
                      List<Answer> answers) {
        this.id = id;
        this.trainingId = trainingId;
        this.questionFormId = questionFormId;
        this.type = type;
        this.answers = answers;
    }


    public AnswerFormId getId() {
        return id;
    }

    public TrainingId getTrainingId() {
        return trainingId;
    }

    public QuestionFormId getQuestionFormId() {
        return questionFormId;
    }

    public QuestionFormType getType() {
        return type;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

}
