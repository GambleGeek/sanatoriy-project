package com.example.client.dao;

import com.example.client.models.Procedure;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcedureMapper implements RowMapper<Procedure> {

    @Override
    public Procedure mapRow(ResultSet resultSet, int rowNum) throws SQLException{
        Procedure procedure = new Procedure();

        procedure.setId(resultSet.getInt("ProcedureID"));
        procedure.setName(resultSet.getString("Name"));
        procedure.setPrice(resultSet.getInt("price"));
        procedure.setStart_at(resultSet.getTime("Start_at"));
        procedure.setEnd_at(resultSet.getTime("End_at"));
        procedure.setSchedule(resultSet.getInt("Schedule"));

        return procedure;
    }
}
