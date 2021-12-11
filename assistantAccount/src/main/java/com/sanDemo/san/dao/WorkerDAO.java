package com.sanDemo.san.dao;

import com.sanDemo.san.models.Worker;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkerDAO {
    private static JdbcTemplate jdbcTemplate;
    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public WorkerDAO(JdbcTemplate jdbcTemplate,
                     NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public static List<Worker> workersList(){
        return jdbcTemplate.query("SELECT * FROM worker", new WorkerMapper());
    }

    public static Worker showWorker(int id){
        return jdbcTemplate.query("SELECT * FROM worker WHERE WorkerID=?", new Object[]{id},
                new WorkerMapper()).stream().findAny().orElse(null);
    }

}
