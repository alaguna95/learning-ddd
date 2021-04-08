package org.alaguna.learningddd.input_data.question_form.infraestructure;

import org.alaguna.learningddd.input_data.question_form.domain.*;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class QuestionFormRepositoryImpl implements QuestionFormRepository{

    private final JdbcTemplate jdbc;

    public QuestionFormRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public QuestionForm findActivePreCompetitionForm() {
        List<Question> questions = findQuestionsByQuestionFormId("1").
                stream().map(this::toQuestion).collect(Collectors.toList());
        return toQuestionForm(findQuestionFormById(), questions);
    }


    private Map<String, Object> findQuestionFormById(){
        return jdbc.queryForObject(" select f.id, f.type, f.active from question_form f where f.type = 'PRE_COMPETITION' and f.active = 1",
                new ColumnMapRowMapper(),
                new Object[]{});
    }

    private QuestionForm toQuestionForm(Map<String, Object> questionFormMap, List<Question> questions){
        return new QuestionForm(new QuestionFormId((String)questionFormMap.get("id")),
                new QuestionFormType(QuestionFormTypeEnum.PRE_COMPETITION),
                new QuestionFormActive(true),
                questions);
    }


    private List<Map<String, Object>> findQuestionsByQuestionFormId(String questionFormId){
        return jdbc.query(
                "select q.id, q.name, q.sentence from question q where q.question_form_id=?",
                new ColumnMapRowMapper(),
                new Object[]{questionFormId});
    }

    private Question toQuestion(Map<String, Object> questionMap){
        return new Question(new QuestionId((String)questionMap.get("id")),
                new QuestionName((String)questionMap.get("name")),
                new QuestionSentence((String)questionMap.get("sentence")));
    }

}
