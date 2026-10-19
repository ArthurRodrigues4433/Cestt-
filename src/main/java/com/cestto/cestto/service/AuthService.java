package com.cestto.cestto.service;

import com.cestto.cestto.dto.AuthRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final  JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String login(AuthRequest authRequest){

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getSenha()
                );

        Authentication authentication =
                authenticationManager.authenticate(token);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return jwtService.generateToken(userPrincipal);
    }
}