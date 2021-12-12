package com.example.demoSan.models;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchase {
    private int ClientID, ProcedureID, PurchaseID, Procedure_taken;
    private java.util.Date Date;
    private java.sql.Time Time;

    public Purchase(){}
    public Purchase(int clientID, int procedureID, int purchaseID, Date date, Time time, int procedure_taken) {
        ClientID = clientID;
        ProcedureID = procedureID;
        PurchaseID = purchaseID;
        Date = date;
        Time = time;
        Procedure_taken = procedure_taken;
    }

    public Purchase(int clientID, int procedureID) {
        ClientID = clientID;
        ProcedureID = procedureID;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }

    public int getProcedureID() {
        return ProcedureID;
    }

    public void setProcedureID(int procedureID) {
        ProcedureID = procedureID;
    }

    public int getPurchaseID() {
        return PurchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        PurchaseID = purchaseID;
    }


    public int getProcedure_taken() {
        return Procedure_taken;
    }

    public void setProcedure_taken(int procedure_taken) {
        Procedure_taken = procedure_taken;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public java.sql.Time getTime() {
        return Time;
    }

    public void setTime(java.sql.Time time) {
        Time = time;
    }

    public String getDateString(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Date);
        return date;
    }

    public String getTimeString(){
        String time = new SimpleDateFormat("HH:mm").format(Time);
        return time;
    }
}
