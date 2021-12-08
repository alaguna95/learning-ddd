package org.alaguna.learningddd.input_data.answer_form.domain;

import org.alaguna.learningddd.input_data.question_form.domain.QuestionFormType;
import org.alaguna.learningddd.input_data.training.domain.TrainingId;

public interface AnswerFormRepository {

    boolean existAnswerFormByTrainingIdAndType(TrainingId trainingId, QuestionFormType type);

    void createAnswerForm(AnswerForm answerForm);
}
