package com.jwtTest.jwt.service;

import org.springframework.stereotype.Component;

@Component
public class TestService {

    private String test = "TEST MESSAGE";

    public String getTest() {
        return test;
    }
}
