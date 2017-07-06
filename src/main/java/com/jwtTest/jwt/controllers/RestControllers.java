/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jwtTest.jwt.controllers;

import com.jwtTest.jwt.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alexey
 */
@RestController
public class RestControllers {
    
    @Autowired
    TestService service;

    @RequestMapping(value = "/user")
    public String userInfo(){
        
        String tt = service.getTest();
        return "user String"+ tt;
    }
    
    @RequestMapping(value = "/admin")
    public String adminInfo(){
        return "admin String";
    }
}
