package com.example.client.dao;

import com.example.client.models.Purchase;
import com.example.client.models.Treatment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    public static void addTreatment(Treatment treatment){
        jdbcTemplate.update("INSERT INTO treatment (ClientID, ProcedureID) VALUES (?, ?)",
                treatment.getClientID(), treatment.getProcedureID());
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
        return jdbcTemplate.query("SELECT * FROM purchase WHERE ClientID=?", new Object[]{id},
                new PurchaseMapper());
    }
}
