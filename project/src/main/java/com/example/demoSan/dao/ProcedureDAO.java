package com.example.demoSan.dao;

import com.example.demoSan.models.Procedure;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProcedureDAO {
    private static JdbcTemplate jdbcTemplate;
    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProcedureDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                        JdbcTemplate jdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    public static Procedure showProcedure(int id){
        return jdbcTemplate.query("SELECT * FROM procedures WHERE ProcedureID=?",
                new Object[]{id}, new ProcedureMapper()).stream().findAny().orElse(null);
    }

    public static List<Procedure> allProcedures(){
        return jdbcTemplate.query("SELECT * FROM `procedures`",
                new ProcedureMapper());
    }

    public static Procedure findProcedureByName(String name){
        return jdbcTemplate.query("SELECT * FROM procedures WHERE Name=?",
                new Object[]{name}, new ProcedureMapper()).stream().findAny().orElse(null);
    }

    public static List<Procedure> myProcedures(int id){
        String SQL = "SELECT * FROM procedures WHERE ProcedureID IN (SELECT ProcedureID FROM treatment WHERE ClientID=:id)";
        Map parameters = new HashMap();
        parameters.put("id", id);
        return namedParameterJdbcTemplate.query(SQL,
                parameters,
                new ProcedureMapper());
    }

    public static List<Procedure> clientBoughtProcedures(int id){
        String SQL = "SELECT * FROM procedures WHERE ProcedureID IN (SELECT ProcedureID FROM purchase WHERE ClientID=:id " +
                "AND procedure_taken=0)";
        Map parameters = new HashMap();
        parameters.put("id", id);
        return namedParameterJdbcTemplate.query(SQL,
                parameters,
                new ProcedureMapper());
    }
    public static List<Procedure> restProcedures(int id){
        String SQL = "SELECT * FROM procedures WHERE ProcedureID NOT IN (SELECT ProcedureID FROM purchase WHERE ClientID=:id " +
                "AND procedure_taken=0)";
        Map parameters = new HashMap();
        parameters.put("id", id);
        return namedParameterJdbcTemplate.query(SQL,
                parameters,
                new ProcedureMapper());
    }

    public static List<Procedure> boughtProcedures(int id){
        String SQL = "SELECT * FROM procedures WHERE ProcedureID IN (SELECT ProcedureID FROM purchase_worker WHERE WorkerID=:id " +
                "AND procedure_taken=0)";
        Map parameters = new HashMap();
        parameters.put("id", id);
        return namedParameterJdbcTemplate.query(SQL,
                parameters,
                new ProcedureMapper());
    }

    public static List<Procedure> boughtProcedure(int workerId, int procedureId){
        String SQL = "SELECT * FROM procedures WHERE ProcedureID=:procedureId AND ProcedureID IN (SELECT ProcedureID FROM purchase_worker WHERE WorkerID=:workerId " +
                "AND procedure_taken=0)";
        Map parameters = new HashMap();
        parameters.put("workerId", workerId);
        parameters.put("procedureId", procedureId);
        return namedParameterJdbcTemplate.query(SQL,
                parameters,
                new ProcedureMapper());
    }

    public static List<Procedure> restProceduresW(int id){
        String SQL = "SELECT * FROM procedures WHERE ProcedureID NOT IN (SELECT ProcedureID FROM purchase_worker WHERE WorkerID=:id " +
                "AND procedure_taken=0)";
        Map parameters = new HashMap();
        parameters.put("id", id);
        return namedParameterJdbcTemplate.query(SQL,
                parameters,
                new ProcedureMapper());
    }

    public static List<Procedure> restProcedure(int workerId, int procedureId){
        String SQL = "SELECT * FROM procedures WHERE ProcedureID=:procedureId AND ProcedureID NOT IN (SELECT ProcedureID FROM purchase_worker WHERE WorkerID=:workerId " +
                "AND procedure_taken=0)";
        Map parameters = new HashMap();
        parameters.put("workerId", workerId);
        parameters.put("procedureId", procedureId);
        return namedParameterJdbcTemplate.query(SQL,
                parameters,
                new ProcedureMapper());
    }

    public static List<Procedure> procedureSchedule(int day){
        String SQL = "SELECT * FROM procedures WHERE ProcedureID IN (SELECT ProcedureID FROM procedures_schedule WHERE day=:day)";
        Map parameters = new HashMap();
        parameters.put("day", day);
        return namedParameterJdbcTemplate.query(SQL,
                parameters,
                new ProcedureMapper());
    }

    public static void setProcedureAsVisited(int procedureId, int clientId){
        jdbcTemplate.update("UPDATE purchase SET procedure_taken=1 WHERE ClientID=? AND ProcedureID=?",
                clientId, procedureId);
    }

    public static void editProcedure(int procedureId, Procedure procedure){
        jdbcTemplate.update("UPDATE procedures SET Name=?, Price=?, Start_at=?, End_at=? WHERE ProcedureID=?",
                procedure.getName(), procedure.getPrice(), procedure.getStart_at(), procedure.getEnd_at(), procedureId);
    }
}
