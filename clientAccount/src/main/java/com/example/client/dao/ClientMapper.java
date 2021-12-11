package com.example.client.dao;

import com.example.client.models.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet resultSet, int rowNum) throws SQLException{
        Client client = new Client();

        client.setId(resultSet.getInt("IDclient"));
        client.setHeight(resultSet.getInt("height"));
        client.setWeight(resultSet.getInt("weight"));
        client.setBloodtype(resultSet.getInt("bloodtype"));
        client.setBirthdate(resultSet.getDate("birthdate"));
        client.setName(resultSet.getString("name"));
        client.setAddress(resultSet.getString("address"));
        client.setGender(resultSet.getString("gender"));
        client.setRezus(resultSet.getString("rezus"));
        client.setIllness(resultSet.getString("illness"));

        return client;
    }

}
