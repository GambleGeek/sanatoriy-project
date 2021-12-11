package com.sanDemo.san.models;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Procedure {
    private int id, price, schedule;
    private String name;
    private Time start_at, end_at;
    final String pattern = "HH:mm";
    final String[] Weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public Procedure(){}

    public Procedure(int id, int price, int schedule, String name, Time start_at, Time end_at) {
        this.id = id;
        this.price = price;
        this.schedule = schedule;
        this.name = name;
        this.start_at = start_at;
        this.end_at = end_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getStart_at() {
        return start_at;
    }

    public String getStringStart_at() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(this.start_at);
    }

    public void setStart_at(Time start_at){
        this.start_at = start_at;
    }

    public Time getEnd_at() {
        return end_at;
    }

    public String getStringEnd_at() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(this.end_at);
    }

    public void setEnd_at(Time end_at) {
        this.end_at = end_at;
    }

    // представляет значение из БД в виде списка, который заполняет значениями двойки в степенях (в зависимости от дня недели)
    public double[] getScheduleArray(){
        double listDays[] = new double[7];
        for (int i=6; i>=0; i--){
            if(schedule-(Math.pow(2,i))>=0){
                listDays[i] = Math.pow(2,i);
                schedule -= Math.pow(2,i);
            }
        }
        return listDays;
    }

    // представляет вычисленный список с числовыми значениями ↑ в виде списка из дней недели, в которые проводится процедура
    public List<String> getDaysOfWeekArray(){
        double[] listDays = getScheduleArray();
        List<String> AllWeekDays = new ArrayList<>();
        for (int i=0; i<7; i++){
            if(listDays[i]!=0){
                AllWeekDays.add(Weekdays[i]);
            }
        }

        return AllWeekDays;
    }

    // превращает полученный список ↑ в строку
    public String getDaysOfWeekString(){
        String list = getDaysOfWeekArray().toString();
        return list.substring(1,list.length()-1);
    }

    public float getPriceDiscount(){
        float result = 0;
        float discount = this.price * 0.5f;
        result = this.price - discount;
        return result;
    }

}
