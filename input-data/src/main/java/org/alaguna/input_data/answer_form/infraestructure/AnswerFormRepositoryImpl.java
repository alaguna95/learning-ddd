package org.alaguna.input_data.answer_form.infraestructure;

import org.alaguna.input_data.answer_form.domain.Answer;
import org.alaguna.input_data.answer_form.domain.AnswerForm;
import org.alaguna.input_data.answer_form.domain.AnswerFormRepository;
import org.alaguna.input_data.question_form.domain.QuestionFormType;
import org.alaguna.input_data.training.domain.TrainingId;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerFormRepositoryImpl implements AnswerFormRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public AnswerFormRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean existAnswerFormByTrainingIdAndType(TrainingId trainingId, QuestionFormType type) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().
            addValue("trainingId", trainingId.value()).
            addValue("type", type.toString());

        String query = "select 1 from answer_form f where f.training_id = :trainingId and f.type = :type";

        return jdbc.query(query, namedParameters, BeanPropertyRowMapper.newInstance(String.class)).size() > 0;
    }

    @Override
    public void createAnswerForm(AnswerForm answerForm) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().
                addValue("id", answerForm.getId().value()).
                addValue("training_id", answerForm.getTrainingId().value()).
                addValue("question_form_id", answerForm.getQuestionFormId().value()).
                addValue("type", answerForm.getType().getValue().toString());

        jdbc.update(
                "insert into answer_form values(:id, :training_id, :question_form_id, :type)", namedParameters);


        for(Answer answer: answerForm.getAnswers()){
            SqlParameterSource namedParametersAnswer = new MapSqlParameterSource().
                    addValue("id", answer.getId().value()).
                    addValue("answer_form_id", answerForm.getId().value()).
                    addValue("question_id", answer.getQuestionId().value()).
                    addValue("value", answer.getValue().value());

            jdbc.update(
                    "insert into answer values(:id, :answer_form_id, :question_id, :value)", namedParametersAnswer);
        }


    }
}
