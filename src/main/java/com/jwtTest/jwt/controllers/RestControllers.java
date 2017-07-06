package com.jwtTest.jwt.controllers;

import com.jwtTest.jwt.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllers {

    @Autowired
    TestService service;

    @RequestMapping(value = "/user")
    public String userInfo() {

        String tt = service.getTest();
        return "user String" + tt;
    }

    @RequestMapping(value = "/admin")
    public String adminInfo() {
        return "admin String";
    }
}
