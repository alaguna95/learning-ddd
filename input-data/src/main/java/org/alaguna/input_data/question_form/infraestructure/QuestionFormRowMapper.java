package org.alaguna.input_data.question_form.infraestructure;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionFormRowMapper implements RowMapper<QuestionFormQueryDTO> {

    @Override
    public QuestionFormQueryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new QuestionFormQueryDTO( rs.getString("id"), rs.getString("type"),
                rs.getBoolean("active"));
    }
}
