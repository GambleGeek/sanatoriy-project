package com.example.demoSan.dao;

import com.example.demoSan.models.Purchase;
import com.example.demoSan.models.Treatment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TreatmentMapper implements RowMapper<Treatment> {
    @Override
    public Treatment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Treatment treatment = new Treatment();

        treatment.setTreatmentID(rs.getInt("TreatmentID"));
        treatment.setClientID(rs.getInt("ClientID"));
        treatment.setProcedureID(rs.getInt("ProcedureID"));
        treatment.setDate(rs.getDate("Date"));
        treatment.setTime(rs.getTime("Time"));
        return treatment;
    }
}
