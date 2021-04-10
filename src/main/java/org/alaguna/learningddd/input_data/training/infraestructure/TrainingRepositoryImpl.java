package org.alaguna.learningddd.input_data.training.infraestructure;

import org.alaguna.learningddd.input_data.training.domain.Training;
import org.alaguna.learningddd.input_data.training.domain.TrainingRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingRepositoryImpl implements TrainingRepository {

    private final JdbcTemplate jdbc;

    public TrainingRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void createTraining(Training training) {
            jdbc.update(
                    "insert into training values(?, ?, ?)", training.getId().value(), training.getPeriod().getStart().value(),
                    training.getPeriod().getFinish().value());
    }
}
