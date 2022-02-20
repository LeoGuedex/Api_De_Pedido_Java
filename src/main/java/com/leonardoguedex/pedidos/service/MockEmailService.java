package com.leonardoguedex.pedidos.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;


public class MockEmailService extends  AbstractEmailService {

    //Receita de bolo
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage mailMessage) {
        LOG.info("Simulando Envio de Email....");
        LOG.info(mailMessage.toString());
        LOG.info("E-mail enviado com sucesso!");

    }
}
