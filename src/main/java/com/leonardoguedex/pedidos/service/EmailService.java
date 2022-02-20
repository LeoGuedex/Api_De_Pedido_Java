package com.leonardoguedex.pedidos.service;

import com.leonardoguedex.pedidos.domain.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage mailMessage);
}
