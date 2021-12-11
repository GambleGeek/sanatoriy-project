package com.sanDemo.san.dao;

import com.sanDemo.san.models.Worker;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerMapper implements RowMapper<Worker> {

    @Override
    public Worker mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Worker worker = new Worker();

        worker.setWorkerId(resultSet.getInt("WorkerID"));
        worker.setName(resultSet.getString("Name"));
        worker.setPosition(resultSet.getString("position"));
        worker.setLogin(resultSet.getString("Login"));
        worker.setPassword(resultSet.getInt("Password"));

        return worker;
    }
}
