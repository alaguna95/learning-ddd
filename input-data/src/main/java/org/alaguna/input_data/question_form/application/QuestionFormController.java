package org.alaguna.input_data.question_form.application;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionFormController {

    private QuestionFormGet questionFormGet;

    public QuestionFormController(QuestionFormGet questionFormGet) {
        this.questionFormGet = questionFormGet;
    }


    @GetMapping("/question-forms")
    public ResponseEntity<QuestionFormOutputDTO> getQuestionForm( @RequestParam(value = "type") String type){
        return ResponseEntity.ok(questionFormGet.getQuestionFormByType(type));
    }

}
