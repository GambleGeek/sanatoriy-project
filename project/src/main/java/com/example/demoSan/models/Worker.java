package com.example.demoSan.models;

import javax.validation.constraints.*;

public class Worker {
    private int workerId;
    @NotEmpty(message="Name should be not empty")
//    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotEmpty(message="Position should be not empty")
    @Pattern(regexp = "assistant")
    @Pattern(regexp = "manager")
//    @NotBlank(message = "Position is mandatory")
    private String position;

//    @NotEmpty(message="Login should be not empty")
//    @NotBlank(message = "Login is mandatory")
    private String login;

    @NotEmpty(message="Password should be not empty")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}",
            message = "Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters")
//    @NotBlank(message = "Name is mandatory")
    private String password;

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String  getPassword() {
        return password;
    }

    public void setPassword(String  password) {
        this.password = password;
    }
}
