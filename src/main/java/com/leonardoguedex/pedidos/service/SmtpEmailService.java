package com.leonardoguedex.pedidos.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends  AbstractEmailService{


    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendEmail(SimpleMailMessage mailMessage) {
        LOG.info("Enviando de Email....");
        mailSender.send(mailMessage);
        LOG.info("E-mail enviado com sucesso!");
    }
}
