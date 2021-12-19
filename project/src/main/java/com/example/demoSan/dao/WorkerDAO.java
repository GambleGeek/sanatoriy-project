package com.example.demoSan.dao;

import com.example.demoSan.models.Worker;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        return jdbcTemplate.query("SELECT * FROM worker WHERE Position<>'director'", new WorkerMapper());
    }

    public static Worker showWorker(int id){
        return jdbcTemplate.query("SELECT * FROM worker WHERE WorkerID=?", new Object[]{id},
                new WorkerMapper()).stream().findAny().orElse(null);
    }

    public static Worker findWorkerByName(String name){
        return jdbcTemplate.query("SELECT * FROM worker WHERE Name=?",
                new Object[]{name}, new WorkerMapper()).stream().findAny().orElse(null);
    }

    public static void save(Worker worker){
        jdbcTemplate.update("INSERT INTO worker (Name, position, Login, Password) VALUES(?, ?, ?, ?)", worker.getName(),
                worker.getPosition(), worker.getLogin(), worker.getPassword());
        saveUser();
    }

    public static void saveUser(){
        int id = jdbcTemplate.queryForObject("SELECT MAX(WorkerID) from worker", Integer.class);
        Worker worker = jdbcTemplate.query("SELECT * FROM worker WHERE WorkerID=?", new Object[]{id}, new WorkerMapper())
                .stream().findAny().orElse(null);
        String encoded = new BCryptPasswordEncoder().encode(worker.getPassword());
        jdbcTemplate.update("INSERT INTO user(id, Name, username, password, role) VALUES(?,?,?,?,?)",
                id, worker.getName(), worker.getLogin(), encoded, worker.getPosition().toLowerCase());
    };

    public static void update(int id, Worker updatedWorker){
        jdbcTemplate.update("UPDATE worker SET Name=?, Position=? WHERE WorkerID=?", updatedWorker.getName(),
                updatedWorker.getPosition(), id);
    }

    public static void delete(int id){
        jdbcTemplate.update("DELETE FROM worker WHERE WorkerID=?", id);
        jdbcTemplate.update("UPDATE user SET isEnabled=0 WHERE id=?", id);
    }
}
