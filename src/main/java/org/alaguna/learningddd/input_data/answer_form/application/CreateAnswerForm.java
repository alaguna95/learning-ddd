package org.alaguna.learningddd.input_data.answer_form.application;

import org.alaguna.learningddd.input_data.answer_form.domain.*;
import org.alaguna.learningddd.input_data.question_form.domain.*;
import org.alaguna.learningddd.input_data.training.domain.Training;
import org.alaguna.learningddd.input_data.training.domain.TrainingId;
import org.alaguna.learningddd.input_data.training.domain.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        if(training == null){
            throw new IllegalArgumentException();
        }

        if(answerFormRepository.existAnswerFormByTrainingIdAndType(new TrainingId(command.getTrainingId()),
                new QuestionFormType(command.getFormType()))){
            throw new IllegalArgumentException();
        }

        if(!thereAreOneAnswerForEachQuestion(questionForm, command)){
            throw new IllegalArgumentException();
        }

        List<Answer> answers = new ArrayList<>();

        for(AnswerCommand answerCommand : command.getAnswers()){
            answers.add(new Answer(new AnswerId(UUID.randomUUID().toString()), new QuestionId(answerCommand.getQuestionId()),
                    new AnswerValue(answerCommand.getValue())));
        }

        AnswerForm answerForm = new AnswerForm(
                new AnswerFormId(UUID.randomUUID().toString()),
                new TrainingId(command.getTrainingId()),
                new QuestionFormId(command.getQuestionFormId()),
                new QuestionFormType(command.getFormType()),
                answers);


        answerFormRepository.createAnswerForm(answerForm);

    }

    private boolean thereAreOneAnswerForEachQuestion(QuestionForm questionForm, AnswerFormCommand answerFormCommand){

        if(questionForm.getQuestions().size()!= answerFormCommand.getAnswers().size()){
            return false;
        }

        for(Question question: questionForm.getQuestions()){
            if(!existAnswerForThisQuestion(answerFormCommand.getAnswers(), question.getId())){
                return false;
            }
        }

        return true;
    }


    private boolean existAnswerForThisQuestion (List<AnswerCommand> answersCommand, QuestionId questionId){
        for(AnswerCommand answerCommand: answersCommand){
            if(answerCommand.getQuestionId().equals(questionId.value())){
                return true;
            }
        }
        return false;
    }


}
