package com.example.demoSan.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demoSan.security.UserPermission.*;

public enum UserRole {
    CLIENT(Sets.newHashSet()),
    ASSISTANT(Sets.newHashSet(CLIENT_READ, CLIENT_WRITE)),
    MANAGER(Sets.newHashSet(WORKER_READ, WORKER_WRITE)),
    DIRECTOR(Sets.newHashSet(WORKER_READ, WORKER_WRITE, CLIENT_READ, CLIENT_WRITE));

    private final Set<UserPermission> permissions;
    UserRole(Set<UserPermission> permissions){
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
