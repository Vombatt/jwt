/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwtTest.jwt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwtTest.jwt.filters.AccountCredentials;
import com.jwtTest.jwt.model.JwtUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.emptyList;
import java.util.Date;
import java.util.Set;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 *
 * @author alexey
 */
@Component
public class TokenAuthenticationService {
    
    @Autowired
    JwtUserService service;
    
    static final long EXPIRATIONTIME = 864_000_000; // время истечения 10 дней
    static final String SECRET = "ThisIsASecret";  // ключ
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    
   

    public static void addAuthentication(HttpServletResponse response, String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
        
    }

    public Authentication getAuthentication(HttpServletRequest request) throws IOException {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        Collection<SimpleGrantedAuthority> authoritys = new ArrayList<>();
        authoritys.add(authority);
        authority= new SimpleGrantedAuthority("ROLE_USER");
        authoritys.add(authority);
        
       
        
        
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            

            JwtUser jwtUser = service.getJwtUserByUsername(user);

            return user != null
                    ? new UsernamePasswordAuthenticationToken(user, null, jwtUser.getAuthorities()/*emptyList()*/) : null;
        }
        return null;
    }
}
