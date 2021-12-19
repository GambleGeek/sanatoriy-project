package com.example.demoSan.security.auth;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User applicationUser = new User();
        applicationUser.setId(resultSet.getInt("id"));
        applicationUser.setUsername(resultSet.getString("username"));
        applicationUser.setPassword(resultSet.getString("password"));
        applicationUser.setAccountNonExpired(resultSet.getBoolean("isAccountNonExpired"));
        applicationUser.setEnabled(resultSet.getBoolean("isEnabled"));
        applicationUser.setAccountNonLocked(resultSet.getBoolean("isAccountNonLocked"));
        applicationUser.setCredentialsNonExpired(resultSet.getBoolean("isCredentialsNonExpired"));
        applicationUser.setRole(resultSet.getString("role"));
        applicationUser.setGrantedAuthorities();
        return applicationUser;
    }
}
