package org.alaguna.input_data.training.infraestructure;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainingRowMapper implements RowMapper<TrainingQueryDTO> {

    @Override
    public TrainingQueryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TrainingQueryDTO(rs.getString("id"), rs.getTimestamp("start").toLocalDateTime(),
                rs.getTimestamp("finish").toLocalDateTime());
    }

}
