package org.alaguna.learningddd.input_data.question_form.application;

import org.alaguna.learningddd.input_data.question_form.domain.Question;
import org.alaguna.learningddd.input_data.question_form.domain.QuestionForm;
import org.alaguna.learningddd.input_data.question_form.infraestructure.QuestionFormRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionFormGet {

    private QuestionFormRepository questionFormRepository;

    public QuestionFormGet(QuestionFormRepository questionFormRepository) {
        this.questionFormRepository = questionFormRepository;
    }

    public QuestionFormOutputDTO getQuestionForm(){
        QuestionForm questionForm =  questionFormRepository.findActivePreCompetitionForm();

        List<QuestionOutputDTO> questionsDTO = new ArrayList<>();
        for(Question question : questionForm.getQuestions()){
            questionsDTO.add(new QuestionOutputDTO(question.getId().value(),question.getName().value(),
                    question.getSentence().value()));
        }

        return new QuestionFormOutputDTO(questionForm.getQuestionFormId().value(), questionsDTO);
    }


}
