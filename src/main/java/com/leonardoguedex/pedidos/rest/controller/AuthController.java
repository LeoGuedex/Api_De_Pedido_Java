package com.leonardoguedex.pedidos.rest.controller;

import com.leonardoguedex.pedidos.rest.dto.EmailDto;
import com.leonardoguedex.pedidos.security.JwtUtil;
import com.leonardoguedex.pedidos.security.UserSS;
import com.leonardoguedex.pedidos.service.AuthService;
import com.leonardoguedex.pedidos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("acess-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDto emailDto){
        authService.sendNewPassword(emailDto.getEmail());
        return ResponseEntity.noContent().build();
    }
}
