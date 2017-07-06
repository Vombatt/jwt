package com.jwtTest.jwt.controllers;

import com.jwtTest.jwt.filters.AccountCredentials;
import com.jwtTest.jwt.service.TokenAuthenticationService;
import java.util.Collections;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/users")
    public String getUsers() {
        return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"},"
                + "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
    }

    @RequestMapping("/auth")
    public ResponseEntity<?> test(@RequestBody AccountCredentials creds, HttpServletResponse res) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(creds.getUsername(),
                        creds.getPassword(),
                        Collections.emptyList()
                )
        );
        TokenAuthenticationService.addAuthentication(res, authentication.getName());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    public String tok(String tok) {
        return tok;
    }

}
