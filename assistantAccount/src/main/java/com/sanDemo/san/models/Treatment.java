package com.sanDemo.san.models;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Treatment {
    private int ClientID, ProcedureID, TreatmentID;
    private java.util.Date Date;
    private java.sql.Time Time;

    public Treatment(){}

    public Treatment(int clientID, int procedureID){
        ClientID = clientID;
        ProcedureID = procedureID;
    }

    public Treatment(int clientID, int procedureID, int treatmentID, Date date, Time time) {
        ClientID = clientID;
        ProcedureID = procedureID;
        TreatmentID = treatmentID;
        Date = date;
        Time = time;
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

    public int getTreatmentID() {
        return TreatmentID;
    }

    public void setTreatmentID(int treatmentID) {
        TreatmentID = treatmentID;
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
