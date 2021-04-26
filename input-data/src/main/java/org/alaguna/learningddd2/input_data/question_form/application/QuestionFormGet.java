package org.alaguna.learningddd2.input_data.question_form.application;

import org.alaguna.learningddd2.input_data.question_form.domain.Question;
import org.alaguna.learningddd2.input_data.question_form.domain.QuestionForm;
import org.alaguna.learningddd2.input_data.question_form.domain.QuestionFormRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionFormGet {

    private QuestionFormRepository questionFormRepository;

    public QuestionFormGet(QuestionFormRepository questionFormRepository) {
        this.questionFormRepository = questionFormRepository;
    }

    public QuestionFormOutputDTO getQuestionFormByType(String type){
        QuestionForm questionForm =  questionFormRepository.getQuestionFormByType(type);

        List<QuestionOutputDTO> questionsDTO = new ArrayList<>();
        for(Question question : questionForm.getQuestions()){
            questionsDTO.add(new QuestionOutputDTO(question.getId().value(),question.getName().value(),
                    question.getSentence().value()));
        }

        return new QuestionFormOutputDTO(questionForm.getQuestionFormId().value(), questionsDTO);
    }


}
