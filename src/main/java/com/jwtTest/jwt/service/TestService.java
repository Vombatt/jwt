/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jwtTest.jwt.service;

import org.springframework.stereotype.Component;

/**
 *
 * @author alexey
 */
@Component
public class TestService {
    private String test = "TEST MESSAGE";
    
    public String getTest(){
        return test;
    }
}
