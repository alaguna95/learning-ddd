package org.alaguna.input_data.question_form.infraestructure;

import org.alaguna.input_data.question_form.domain.*;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class QuestionFormRepositoryImpl implements QuestionFormRepository{

    private final NamedParameterJdbcTemplate jdbc;

    public QuestionFormRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public QuestionForm getQuestionFormByType(String type) {
        QuestionFormQueryDTO questionFormQuery = findQuestionFormByType(type);

        List<Question> questions = findQuestionsByQuestionFormId(questionFormQuery.getId()).
                stream().map(this::toQuestion).collect(Collectors.toList());

        return toQuestionForm(questionFormQuery, questions);
    }


    private QuestionFormQueryDTO findQuestionFormByType(String type){
        SqlParameterSource namedParameters = new MapSqlParameterSource().
                addValue("type", type);

        return jdbc.queryForObject(" select f.id, f.type, f.active from question_form f where f.type = :type and f.active = 1",
                namedParameters,
                new QuestionFormRowMapper());
    }

    private QuestionForm toQuestionForm(QuestionFormQueryDTO questionFormQuery, List<Question> questions){
        return new QuestionForm(new QuestionFormId(questionFormQuery.getId()),
                new QuestionFormType(questionFormQuery.getType()),
                new QuestionFormActive(questionFormQuery.getActive()),
                questions);
    }


    private List<Map<String, Object>> findQuestionsByQuestionFormId(String questionFormId){
        SqlParameterSource namedParameters = new MapSqlParameterSource().
                addValue("questionFormId", questionFormId);

        return jdbc.query(
                "select q.id, q.name, q.sentence from question q where q.question_form_id = :questionFormId",
                namedParameters,
                new ColumnMapRowMapper());
    }

    private Question toQuestion(Map<String, Object> questionMap){
        return new Question(new QuestionId((String)questionMap.get("id")),
                new QuestionName((String)questionMap.get("name")),
                new QuestionSentence((String)questionMap.get("sentence")));
    }

}
