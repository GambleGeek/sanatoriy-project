package com.sanDemo.san.dao;

import com.sanDemo.san.models.PurchaseWorker;
import com.sanDemo.san.models.Treatment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TreatmentPurchaseDAO {
    private static JdbcTemplate jdbcTemplate;
    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TreatmentPurchaseDAO(JdbcTemplate jdbcTemplate,
                                NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public static void addTreatment(Treatment t){
        Date currentDate = new Date();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
        String currTime = new SimpleDateFormat("HH:mm").format(currentDate);
        String SQL = "INSERT INTO treatment (ClientID, ProcedureID, `Date`, `Time`)" +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(SQL, t.getClientID(), t.getProcedureID(), today, currTime);
    }

    public static List<Treatment> treatmentTime(int clientID, int procedureID){
        String sql = "SELECT Date, Time FROM treatment WHERE ClientID=:clientId AND ProcedureID=:procedureId";
        Map parameters = new HashMap();
        parameters.put("clientId", clientID);
        parameters.put("procedureId", procedureID);
        return namedParameterJdbcTemplate.query(sql, parameters, new TreatmentMapper());
    }

    public static PurchaseWorker showLastPurchase(){
        return jdbcTemplate.query("SELECT * FROM purchase_worker WHERE PurchaseID=(SELECT MAX(PurchaseID) FROM purchase_worker)",
                new PurchaseWorkerMapper())
                .stream().findAny().orElse(null);
    }

    public static void addPurchase(PurchaseWorker p){
        Date currentDate = new Date();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
        String currTime = new SimpleDateFormat("HH:mm").format(currentDate);
        String SQL = "INSERT INTO purchase_worker (WorkerID, ProcedureID, `Date`, `Time`, procedure_taken)" +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(SQL, p.getWorkerID(), p.getProcedureID(), today, currTime, 0);
    }

    public static PurchaseWorker showPurchase(int id){
        return jdbcTemplate.query("SELECT * FROM purchase_worker WHERE PurchaseID=?", new Object[]{id},
                new PurchaseWorkerMapper()).stream().findAny().orElse(null);
    }

    public static List<PurchaseWorker> showAllPurchases(int id){
        return jdbcTemplate.query("SELECT * FROM purchase_worker WHERE WorkerID=?", new Object[]{id},
                new PurchaseWorkerMapper());
    }
}
