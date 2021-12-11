package com.example.client.dao;

import com.example.client.models.Procedure;
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

    public static Procedure show(int id){
        return jdbcTemplate.query("SELECT * FROM procedures WHERE ProcedureID=?",
                new Object[]{id}, new ProcedureMapper()).stream().findAny().orElse(null);
    }

    public static List<Procedure> allProcedures(){
        return jdbcTemplate.query("SELECT * FROM `procedures`",
                new ProcedureMapper());
    }

    public static List<Procedure> myProcedures(int id){
        String SQL = "SELECT * FROM procedures WHERE ProcedureID IN (SELECT ProcedureID FROM treatment WHERE ClientID=:id)";
        Map parameters = new HashMap();
        parameters.put("id", id);
        return namedParameterJdbcTemplate.query(SQL,
                parameters,
                new ProcedureMapper());
    }

    public static List<Procedure> boughtProcedures(int id){
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
}
