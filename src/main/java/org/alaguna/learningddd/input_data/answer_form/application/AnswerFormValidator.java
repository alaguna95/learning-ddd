package org.alaguna.learningddd.input_data.answer_form.application;

import org.alaguna.learningddd.input_data.answer_form.domain.AnswerFormRepository;
import org.alaguna.learningddd.input_data.question_form.domain.Question;
import org.alaguna.learningddd.input_data.question_form.domain.QuestionForm;
import org.alaguna.learningddd.input_data.question_form.domain.QuestionFormType;
import org.alaguna.learningddd.input_data.question_form.domain.QuestionId;
import org.alaguna.learningddd.input_data.training.domain.Training;
import org.alaguna.learningddd.input_data.training.domain.TrainingId;

import java.util.List;
import java.util.UUID;

public class AnswerFormValidator {

    private AnswerFormRepository answerFormRepository;

    public AnswerFormValidator(AnswerFormRepository answerFormRepository) {
        this.answerFormRepository = answerFormRepository;
    }

    public void checkAnswerFormIsValid(AnswerFormCommand command, Training training, QuestionForm questionForm){

        checkCommandIsValid(command);

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
    }

    private void checkCommandIsValid(AnswerFormCommand command){
        UUID.fromString(command.getId());

        if(command.getTrainingId() == null){
            throw new IllegalArgumentException();
        }

        if(command.getFormType() == null ){
            throw new IllegalArgumentException();
        }

        if(command.getQuestionFormId() == null){
            throw  new IllegalArgumentException();
        }

        if(command.getAnswers() == null){
            throw new IllegalArgumentException();
        }

        for(AnswerCommand answerCommand: command.getAnswers()){
            UUID.fromString(answerCommand.getId());

            if(answerCommand.getQuestionId() ==null){
                throw new IllegalArgumentException();
            }

            if(answerCommand.getValue() == null){
                throw new IllegalArgumentException();
            }

            if(answerCommand.getValue() < 0 || answerCommand.getValue() > 10){
                throw new IllegalArgumentException();
            }
        }
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
