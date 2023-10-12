package com.bookflix.bookstore.utils;


import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.bookflix.bookstore.utils.Permission.ADMIN_READ;
import static com.bookflix.bookstore.utils.Permission.ADMIN_CREATE;
import static com.bookflix.bookstore.utils.Permission.ADMIN_UPDATE;
import static com.bookflix.bookstore.utils.Permission.ADMIN_DELETE;

@RequiredArgsConstructor
public enum Role {
    
    USER(Collections.emptySet()),

    ADMIN(
        Set.of(
            ADMIN_READ,
            ADMIN_CREATE,
            ADMIN_UPDATE,
            ADMIN_DELETE
        )
    )

    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities = getPermissions()
                                                        .stream()
                                                        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                                                        .collect(Collectors.toList());


        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
