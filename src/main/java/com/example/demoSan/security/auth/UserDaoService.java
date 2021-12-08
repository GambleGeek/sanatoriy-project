package com.example.demoSan.security.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("database")
public class UserDaoService implements UserDao{
    private static JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDaoService(PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(User -> username.equals(User.getUsername()))
                .findFirst();
    }

    public List<User> getApplicationUsers(){
        List<User> applicationUsers = Lists.newArrayList(
                jdbcTemplate.query("SELECT * FROM user", new UserMapper())
        );
        return applicationUsers;
    }
}
