package com.sanDemo.san.dao;

import com.sanDemo.san.models.Reserved;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservedMapper implements RowMapper<Reserved> {
    @Override
    public Reserved mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reserved reserved = new Reserved();

        reserved.setReservationN(rs.getInt("ReservationN"));
        reserved.setClientID(rs.getInt("ClientID"));
        reserved.setRoomID(rs.getInt("RoomID"));
        reserved.setArrived(rs.getDate("Arrived"));
        reserved.setMoved_out(rs.getDate("Moved_out"));

        return reserved;
    }
}
