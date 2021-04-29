package org.alaguna.learningddd.input_data.answer_form.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerFormController {

    private AnswerFormCreate createAnswerForm;

    public AnswerFormController(AnswerFormCreate createAnswerForm) {
        this.createAnswerForm = createAnswerForm;
    }

    @PostMapping("/answer-forms")
    public ResponseEntity<Object> createTraining(@RequestBody AnswerFormCommand command){
        createAnswerForm.create(command);
        return ResponseEntity.noContent().build();
    }
}
