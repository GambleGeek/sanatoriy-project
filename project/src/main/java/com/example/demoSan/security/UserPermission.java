package com.example.demoSan.security;

public enum UserPermission {
    CLIENT_READ("client:read"),
    CLIENT_WRITE("client:read"),
    WORKER_READ("worker:read"),
    WORKER_WRITE("worker:write");

    private final String permission;
    UserPermission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
