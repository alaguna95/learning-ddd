package org.alaguna.dashboard.training_counter.infraestructure;

import org.alaguna.dashboard.training_counter.domain.TrainingCounter;
import org.alaguna.dashboard.training_counter.domain.TrainingCounterRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingCounterRepositoryImpl implements TrainingCounterRepository {

    private static final String TRAINING_COUNTER_ID = "1";

    private final NamedParameterJdbcTemplate jdbc;

    public TrainingCounterRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public TrainingCounter getTrainingCounter(){
        SqlParameterSource namedParameters = new MapSqlParameterSource().
                addValue("id", TRAINING_COUNTER_ID);

        /*return*/ jdbc.queryForObject(" select id, total ,trainings_ids from training_counter where id = :id",
                namedParameters,
                new TrainingCounterRowMapper());

        return null;
    }


}
