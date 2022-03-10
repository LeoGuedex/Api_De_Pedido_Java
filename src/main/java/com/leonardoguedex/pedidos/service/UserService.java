package com.leonardoguedex.pedidos.service;

import com.leonardoguedex.pedidos.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticated(){
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception E){
            return null;
        }
    }


}
