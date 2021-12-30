package com.example.demoSan.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Client {
    private int id, bloodtype, procedureNumber;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Address should not be empty")
    String address;
    @NotNull(message = "Height should not be null")
    int height;
    @NotNull(message = "Weight should not be null")
    int weight;
    @NotEmpty(message = "Illness should not be empty")
    String illness;
    String gender, rezus;
    private Date birthdate;

    public Client(int id, int procedureNumber) {
        this.id = id;
        this.procedureNumber = procedureNumber;
    }

    public Client(){}

    public Client(int id, int height, int weight, int bloodtype,
                  String name, String address, String gender, String rezus, String illness,
                  Date birthdate) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.bloodtype = bloodtype;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.rezus = rezus;
        this.illness = illness;
        this.birthdate = birthdate;
    }


    public int getProcedureNumber() {
        return procedureNumber;
    }

    public void setProcedureNumber(int procedureNumber) {
        this.procedureNumber = procedureNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(int bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRezus() {
        return rezus;
    }

    public void setRezus(String rezus) {
        this.rezus = rezus;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
