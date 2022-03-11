package com.leonardoguedex.pedidos.rest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.beans.XMLEncoder;
import java.io.Serializable;

public class EmailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Email(message = "Email invalido")
    private String email;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
