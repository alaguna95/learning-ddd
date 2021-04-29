package org.alaguna.learningddd.input_data.training.infraestructure;

import org.alaguna.learningddd.input_data.question_form.infraestructure.QuestionFormQueryDTO;
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
