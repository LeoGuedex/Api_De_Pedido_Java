package com.leonardoguedex.pedidos.service;

import com.leonardoguedex.pedidos.domain.entity.Cliente;
import com.leonardoguedex.pedidos.domain.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage mailMessage);

    void sendOrderconfirmationHtmlEmail(Pedido pedido);

    void sendHtmlEmail(MimeMessage mailMessage);

    void sendNewPassword(Cliente cliente, String newPass);

}
