package com.sanDemo.san.dao;

import com.sanDemo.san.models.PurchaseWorker;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseWorkerMapper implements RowMapper<PurchaseWorker> {
    @Override
    public PurchaseWorker mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        PurchaseWorker purchaseWorker = new PurchaseWorker();

        purchaseWorker.setPurchaseID(resultSet.getInt("PurchaseID"));
        purchaseWorker.setWorkerID(resultSet.getInt("WorkerID"));
        purchaseWorker.setProcedureID(resultSet.getInt("ProcedureID"));
        purchaseWorker.setDate(resultSet.getDate("Date"));
        purchaseWorker.setTime(resultSet.getTime("Time"));
        purchaseWorker.setProcedure_taken(resultSet.getInt("Procedure_taken"));

        return purchaseWorker;
    }
}
