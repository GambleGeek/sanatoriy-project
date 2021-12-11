package com.example.client.dao;

import com.example.client.models.Client;
import com.example.client.models.Reserved;
import com.fasterxml.jackson.databind.util.Named;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClientDAO {
    private static JdbcTemplate jdbcTemplate;
    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClientDAO(JdbcTemplate jdbcTemplate,
                     NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public static List<Client> clientList(){
        return jdbcTemplate.query("SELECT * FROM client", new ClientMapper());
    }

    public static Client showClient(int id){
        return jdbcTemplate.query("SELECT * FROM client WHERE IDclient=?", new Object[]{id},
                new ClientMapper()).stream().findAny().orElse(null);
    }

    public static Reserved showReservation(int clientId){
        return jdbcTemplate.query("SELECT * FROM reserved WHERE ClientID=?",
                new Object[]{clientId}, new ReservedMapper())
                .stream().findAny().orElse(null);
    }

}
