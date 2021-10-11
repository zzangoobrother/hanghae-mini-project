package com.miniproject.spring.security;

import com.miniproject.spring.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {

        this.user = user;
    }


    @Override
    public String getUsername() {

        return user.getEmail();
    }

    @Override
    public String getPassword() {

        return user.getPw();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.emptyList();
    }

}

