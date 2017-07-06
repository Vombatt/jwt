package com.jwtTest.jwt.service;

import com.jwtTest.jwt.model.JwtUser;
import com.jwtTest.jwt.model.User;
import com.jwtTest.jwt.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "customUserDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    public UserRepository repository;

    @Autowired
    public JwtUserFactory factory;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        List<User> user = repository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + name);
        } else {
            JwtUser jwtUser = new JwtUser();
            jwtUser = factory.createJwtUser(user.get(0));
            return jwtUser;
        }
    }

}
