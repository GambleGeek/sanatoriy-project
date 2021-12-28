package com.example.demoSan.dao;

import com.example.demoSan.models.Purchase;
import com.example.demoSan.models.PurchaseWorker;
import com.example.demoSan.models.Treatment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

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

    public static List<Treatment> treatmentHistory(int clientID, int procedureID){
        String sql = "SELECT * FROM treatment WHERE ClientID=:clientId AND ProcedureID=:procedureId";
        Map parameters = new HashMap();
        parameters.put("clientId", clientID);
        parameters.put("procedureId", procedureID);
        return namedParameterJdbcTemplate.query(sql, parameters, new TreatmentMapper());
    }

    public static Purchase showLastPurchase(){
        return jdbcTemplate.query("SELECT * FROM purchase WHERE PurchaseID=(SELECT MAX(PurchaseID) FROM purchase)",
                new PurchaseMapper())
                .stream().findAny().orElse(null);
    }

    public static void addPurchase(Purchase p){
        Date currentDate = new Date();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
        String currTime = new SimpleDateFormat("HH:mm").format(currentDate);
        String SQL = "INSERT INTO purchase (ClientID, ProcedureID, `Date`, `Time`, procedure_taken)" +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(SQL, p.getClientID(), p.getProcedureID(), today, currTime, 0);
    }

    public static Purchase showPurchase(int id){
        return jdbcTemplate.query("SELECT * FROM purchase WHERE PurchaseID=?", new Object[]{id},
                new PurchaseMapper()).stream().findAny().orElse(null);
    }

    public static List<Purchase> showAllPurchases(int id){
//        String SQL = "SELECT p.Name, p.ProcedureID, t.Date, t.Time, t.TreatmentID\n" +
//                "FROM procedures p INNER JOIN treatment t\n" +
//                "WHERE t.ClientID=?";
        return jdbcTemplate.query("SELECT * FROM purchase WHERE ClientID=?", new Object[]{id},
                new PurchaseMapper());
    }

    //assistant
    public static List<Treatment> treatmentTime(int clientID, int procedureID){
        String sql = "SELECT Date, Time FROM treatment WHERE ClientID=:clientId AND ProcedureID=:procedureId";
        Map parameters = new HashMap();
        parameters.put("clientId", clientID);
        parameters.put("procedureId", procedureID);
        return namedParameterJdbcTemplate.query(sql, parameters, new TreatmentMapper());
    }

    public static PurchaseWorker showLastPurchaseW(){
        return jdbcTemplate.query("SELECT * FROM purchase_worker WHERE PurchaseID=(SELECT MAX(PurchaseID) FROM purchase_worker)",
                new PurchaseWorkerMapper())
                .stream().findAny().orElse(null);
    }

    public static void addPurchaseW(PurchaseWorker p){
        Date currentDate = new Date();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
        String currTime = new SimpleDateFormat("HH:mm").format(currentDate);
        String SQL = "INSERT INTO purchase_worker (WorkerID, ProcedureID, `Date`, `Time`, procedure_taken)" +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(SQL, p.getWorkerID(), p.getProcedureID(), today, currTime, 0);
    }

    public static PurchaseWorker showPurchaseW(int id){
        return jdbcTemplate.query("SELECT * FROM purchase_worker WHERE PurchaseID=?", new Object[]{id},
                new PurchaseWorkerMapper()).stream().findAny().orElse(null);
    }

    public static List<PurchaseWorker> showAllPurchasesW(int id){
        return jdbcTemplate.query("SELECT * FROM purchase_worker WHERE WorkerID=?", new Object[]{id},
                new PurchaseWorkerMapper());
    }

    public static List<Treatment> showLastTreatments(int clientId){
        List procedures = jdbcTemplate.queryForList("SELECT DISTINCT ProcedureID FROM treatment WHERE ClientID=?",
                new Object[]{clientId}, Integer.class);
        ArrayList<Treatment> treatments = new ArrayList<>();
        Map parameters = new HashMap();
        parameters.put("clientID", clientId);
        parameters.put("procedureID", null);
        String SQL = "SELECT p.Name, p.ProcedureID, t.Date, t.Time, t.TreatmentID\n" +
                "FROM procedures p INNER JOIN treatment t\n" +
                "WHERE t.TreatmentID=(\n" +
                "  SELECT MAX(t.TreatmentID)\n" +
                "    FROM treatment t\n" +
                "    WHERE t.ProcedureID = ? AND t.ClientID = ?\n" +
                ") AND p.ProcedureID = ?";
        for (Object i: procedures){
            parameters.replace("procedureID", i);
            treatments.add(jdbcTemplate.queryForObject(SQL, new Object[]{i, clientId, i}, new TreatmentWithNameMapper()));
        }

        return treatments;
    }
}
