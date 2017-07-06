package com.jwtTest.jwt.service;

import com.jwtTest.jwt.model.JwtUser;
import com.jwtTest.jwt.model.User;
import com.jwtTest.jwt.model.UserAuthorities;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtUserFactory {

    public JwtUser createJwtUser(User user) {
        return new JwtUser(user.getName(), user.getPassword(), getGrantAuthorities(user.getjpaTestUserAuthorites()));
    }

    private List<GrantedAuthority> getGrantAuthorities(List<UserAuthorities> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole()))
                .collect(Collectors.toList());
    }
}
