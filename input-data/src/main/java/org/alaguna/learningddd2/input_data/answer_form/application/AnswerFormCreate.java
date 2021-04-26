package org.alaguna.learningddd2.input_data.answer_form.application;

import org.alaguna.learningddd2.input_data.answer_form.domain.*;
import org.alaguna.learningddd2.input_data.question_form.domain.*;
import org.alaguna.learningddd2.input_data.training.domain.Training;
import org.alaguna.learningddd2.input_data.training.domain.TrainingId;
import org.alaguna.learningddd2.input_data.training.domain.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerFormCreate {

    private QuestionFormRepository questionFormRepository;
    private TrainingRepository trainingRepository;
    private AnswerFormValidator answerFormValidator;
    private AnswerFormRepository answerFormRepository;

    public AnswerFormCreate(QuestionFormRepository questionFormRepository, TrainingRepository trainingRepository,
                            AnswerFormValidator answerFormValidator, AnswerFormRepository answerFormRepository) {
        this.questionFormRepository = questionFormRepository;
        this.trainingRepository = trainingRepository;
        this.answerFormValidator = answerFormValidator;
        this.answerFormRepository = answerFormRepository;
    }

    public void create(AnswerFormCommand command){
        Training training = trainingRepository.getTrainingById(command.getTrainingId());
        QuestionForm questionForm = questionFormRepository.getQuestionFormByType(command.getFormType());

        answerFormValidator.checkAnswerFormIsValid(command, training, questionForm);

        List<Answer> answers = new ArrayList<>();

        for(AnswerCommand answerCommand : command.getAnswers()){
            answers.add(new Answer(new AnswerId(answerCommand.getId()), new QuestionId(answerCommand.getQuestionId()),
                    new AnswerValue(answerCommand.getValue())));
        }

        AnswerForm answerForm = new AnswerForm(
                new AnswerFormId(command.getId()),
                new TrainingId(command.getTrainingId()),
                new QuestionFormId(command.getQuestionFormId()),
                new QuestionFormType(command.getFormType()),
                answers);


        answerFormRepository.createAnswerForm(answerForm);

    }




}
