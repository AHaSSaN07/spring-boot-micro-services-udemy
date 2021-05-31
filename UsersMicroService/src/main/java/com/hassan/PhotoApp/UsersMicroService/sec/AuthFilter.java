package com.hassan.PhotoApp.UsersMicroService.sec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hassan.PhotoApp.UsersMicroService.Models.UserDTO;
import com.hassan.PhotoApp.UsersMicroService.Models.UserLogin;
import com.hassan.PhotoApp.UsersMicroService.Services.UserService;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {
    private UserService usersService;
    private Environment environment;

    public  AuthFilter(UserService usersService,
                                     Environment environment,
                                     AuthenticationManager authenticationManager) {
        this.usersService = usersService;
        this.environment = environment;
        super.setAuthenticationManager(authenticationManager);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            UserLogin cred = new ObjectMapper().readValue(request.getInputStream(),UserLogin.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            cred.getEmail(),
                            cred.getPassword()
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String userName = ((User) auth.getPrincipal()).getUsername();
        UserDTO userDTO = this.usersService.getUserDetailsByUserName(userName);
        
        String token = Jwts.builder().setSubject(userDTO.getUserID())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expirationTime"))))
                .signWith(SignatureAlgorithm.HS512,environment.getProperty("token.secret"))
                .compact();
        res.addHeader("token" ,token);
        res.addHeader("userID",userDTO.getUserID());
    }
}
