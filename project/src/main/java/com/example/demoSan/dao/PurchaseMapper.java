package com.example.demoSan.dao;

import com.example.demoSan.models.Purchase;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseMapper implements RowMapper<Purchase> {

    @Override
    public Purchase mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Purchase purchase = new Purchase();

        purchase.setPurchaseID(resultSet.getInt("PurchaseID"));
        purchase.setClientID(resultSet.getInt("ClientID"));
        purchase.setProcedureID(resultSet.getInt("ProcedureID"));
        purchase.setDate(resultSet.getDate("Date"));
        purchase.setTime(resultSet.getTime("Time"));
        purchase.setProcedure_taken(resultSet.getInt("Procedure_taken"));

        return purchase;
    }
}
