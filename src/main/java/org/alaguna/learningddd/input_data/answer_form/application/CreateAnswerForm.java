package org.alaguna.learningddd.input_data.answer_form.application;

import org.alaguna.learningddd.input_data.answer_form.domain.AnswerForm;
import org.alaguna.learningddd.input_data.answer_form.domain.AnswerFormRepository;
import org.alaguna.learningddd.input_data.question_form.domain.QuestionForm;
import org.alaguna.learningddd.input_data.question_form.domain.QuestionFormRepository;
import org.alaguna.learningddd.input_data.training.domain.Training;
import org.alaguna.learningddd.input_data.training.domain.TrainingRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateAnswerForm {

    private QuestionFormRepository questionFormRepository;
    private AnswerFormRepository answerFormRepository;
    private TrainingRepository trainingRepository;

    public CreateAnswerForm(QuestionFormRepository questionFormRepository, AnswerFormRepository answerFormRepository, TrainingRepository trainingRepository) {
        this.questionFormRepository = questionFormRepository;
        this.answerFormRepository = answerFormRepository;
        this.trainingRepository = trainingRepository;
    }

    public void create(AnswerFormCommand command){
        QuestionForm questionForm = questionFormRepository.getQuestionFormByType(command.getFormType());
        Training training = trainingRepository.getTrainingById(command.getTrainingId());

    }
}
