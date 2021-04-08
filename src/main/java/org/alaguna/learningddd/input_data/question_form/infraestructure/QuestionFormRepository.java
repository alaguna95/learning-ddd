package org.alaguna.learningddd.input_data.question_form.infraestructure;

import org.alaguna.learningddd.input_data.question_form.domain.QuestionForm;
import org.springframework.data.repository.CrudRepository;

public interface QuestionFormRepository {

        QuestionForm findActivePreCompetitionForm();



}
