/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwtTest.jwt.config;

import com.jwtTest.jwt.filters.JWTAuthenticationFilter;
import com.jwtTest.jwt.filters.JWTLoginFilter;
import com.jwtTest.jwt.repository.UserRepository;
import com.jwtTest.jwt.service.JwtUserService;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author alexey
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/springTest";
    String username = "root";
    String password = "";

    

    @Autowired
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().dataSource(getDataSource())
                .usersByUsernameQuery("select name,password, enabled from user where name=?")
                .authoritiesByUsernameQuery("select username, role from user_authorities where username=?");
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/**/*.js").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                                .antMatchers("/user").access("hasRole('USER')")
                                .antMatchers("/admin").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/auth", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // Create a default account
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("password")
//                .roles("ADMIN");
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("user")
//                .roles("USER");
//        
//        
//    }
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("=============DataSourceCreated============");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        return dataSource;
    }
    
//    @Bean
//    public JwtUserService getJwtUserService(){
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
//        System.out.println("=============JwtUserService===============");
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
//        return new JwtUserService();
//    }
    
    
}
