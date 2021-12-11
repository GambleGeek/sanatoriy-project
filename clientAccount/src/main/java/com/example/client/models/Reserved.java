package com.example.client.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserved {
    private int ReservationN, ClientID, RoomID;
    private Date Arrived, Moved_out;

    public Reserved(){}

    public Reserved(int reservationN, int clientID, int roomID, Date arrived, Date moved_out) {
        ReservationN = reservationN;
        ClientID = clientID;
        RoomID = roomID;
        Arrived = arrived;
        Moved_out = moved_out;
    }

    public int getReservationN() {
        return ReservationN;
    }

    public void setReservationN(int reservationN) {
        ReservationN = reservationN;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int roomID) {
        RoomID = roomID;
    }

    public Date getArrived() {
        return Arrived;
    }

    public void setArrived(Date arrived) {
        Arrived = arrived;
    }

    public Date getMoved_out() {
        return Moved_out;
    }

    public void setMoved_out(Date moved_out) {
        Moved_out = moved_out;
    }

    public String getArrivedString(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Arrived);
        return date;
    }

    public String getMoved_outString(){
        if (Moved_out==null){
            return "----";
        }
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Moved_out);
        return date;
    }
}
