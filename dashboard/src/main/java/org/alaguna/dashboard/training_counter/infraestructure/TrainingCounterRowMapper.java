package org.alaguna.dashboard.training_counter.infraestructure;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainingCounterRowMapper implements RowMapper<TrainingCounterQueryDTO> {

    @Override
    public TrainingCounterQueryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        System.out.println(rs.getString("trainings_ids"));

        return null;
    }
}
