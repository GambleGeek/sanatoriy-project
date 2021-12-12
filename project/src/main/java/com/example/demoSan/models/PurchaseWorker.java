package com.example.demoSan.models;

import java.text.SimpleDateFormat;

public class PurchaseWorker {
    private int WorkerID, ProcedureID, PurchaseID, Procedure_taken;
    private java.util.Date Date;
    private java.sql.Time Time;

    public PurchaseWorker(){}

    public PurchaseWorker(int workerID, int procedureID, int purchaseID, int procedure_taken, java.util.Date date, java.sql.Time time) {
        WorkerID = workerID;
        ProcedureID = procedureID;
        PurchaseID = purchaseID;
        Procedure_taken = procedure_taken;
        Date = date;
        Time = time;
    }

    public PurchaseWorker(int workerID, int procedureID) {
        WorkerID = workerID;
        ProcedureID = procedureID;
    }

    public int getWorkerID() {
        return WorkerID;
    }

    public void setWorkerID(int workerID) {
        WorkerID = workerID;
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
