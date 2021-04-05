package org.alaguna.learningddd.input_data.question_form.infraestructure;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

interface QuestionFormRepository extends CrudRepository<QuestionFormEntity, Long> {

        @Query("SELECT p.* FROM question_form f where f.type = 'PRE_COMPETITION' and f.active = 1 ")
        QuestionFormEntity findActivePreCompetitionForm();

        @Query("SELECT p.* FROM question_form f where f.type = 'POST_COMPETITION' and f.active = 1 ")
        QuestionFormEntity findActivePostCompetitionForm();

}
