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
import com.jwtTest.jwt.service.TestService;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Bean
    public JWTAuthenticationFilter authenticationFilter(){
        return new JWTAuthenticationFilter();
    }
    
    @Bean
    public JWTLoginFilter loginFilter(AuthenticationManager manager){
        return new JWTLoginFilter("/auth", manager);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }
    
    @Autowired
    @Qualifier(value = "customUserDetailsService")
    public UserDetailsService detailsService;

    @Autowired
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder.jdbcAuthentication().dataSource(getDataSource())
//                .usersByUsernameQuery("select name,password, enabled from user where name=?")
//                .authoritiesByUsernameQuery("select username, role from user_authorities where username=?");
        builder.userDetailsService(detailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/**/*.js").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                                .antMatchers("/user").access("hasRole('USER') or hasRole('ADMIN')")
                                .antMatchers("/admin").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
//                .addFilterBefore(loginFilter("/auth", authenticationManager()),
//                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(loginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationFilter(),
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
//    public TestService getTestService(){
//        return new TestService();
//    }
    
//    @Bean
//    public JwtUserService getJwtUserService(){
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
//        System.out.println("=============JwtUserService===============");
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
//        return new JwtUserService();
//    }
    
    
}
