package org.alaguna.learningddd.input_data.training.infraestructure;

import org.alaguna.learningddd.input_data.training.domain.Training;
import org.alaguna.learningddd.input_data.training.domain.TrainingRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class TrainingRepositoryImpl implements TrainingRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public TrainingRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void createTraining(Training training) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().
                addValue("id", training.getId().value()).
                addValue("start", training.getPeriod().getStart().value()).
                addValue("end", training.getPeriod().getFinish().value());

        jdbc.update(
                "insert into training values(:id, :start, :end)", namedParameters);
    }

    @Override
    public boolean existSomeTrainingInThisPeriod(LocalDateTime start, LocalDateTime finish) {

        SqlParameterSource namedParameters = new MapSqlParameterSource().
                addValue("start", start).
                addValue("finish", finish);

        String query = "select 1 from training t " +
                "where (:start >= t.start and :start < t.finish) " +
                "or (:finish > t.start and :finish <= t.finish) " +
                "or (:start < start and :finish > t.finish)";

        return jdbc.query(query, namedParameters, BeanPropertyRowMapper.newInstance(String.class)).size() > 0;
    }
}
