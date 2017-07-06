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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
                .addFilterBefore(loginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }
}
