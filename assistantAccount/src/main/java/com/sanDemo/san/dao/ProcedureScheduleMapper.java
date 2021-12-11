package com.sanDemo.san.dao;

import com.sanDemo.san.models.ProceduresSchedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcedureScheduleMapper implements RowMapper<ProceduresSchedule> {
    @Override
    public ProceduresSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProceduresSchedule proceduresSchedule = new ProceduresSchedule();

        proceduresSchedule.setDay(rs.getInt("day"));
        proceduresSchedule.setProcedureId(rs.getInt("ProcedureID"));

        return proceduresSchedule;
    }
}
