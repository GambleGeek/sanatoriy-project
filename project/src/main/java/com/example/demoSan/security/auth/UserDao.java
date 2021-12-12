package com.example.demoSan.security.auth;

import java.util.Optional;

public interface UserDao {
    Optional<User> selectApplicationUserByUsername(String username);

}
