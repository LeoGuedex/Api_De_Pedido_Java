package com.leonardoguedex.pedidos.service;


import com.leonardoguedex.pedidos.domain.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(simpleMailMessage);
    }

//    @Override
//    public void sendEmail(SimpleMailMessage mailMessage) {}

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(pedido.getCliente().getEmail());
        simpleMailMessage.setFrom("leo.correa.guedes@gmail.com");
        simpleMailMessage.setSubject("Pedido Confirmado! Código: " + pedido.getId());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(pedido.toString());
        return simpleMailMessage;
    }

    @Override
    public void sendOrderconfirmationHtmlEmail(Pedido pedido) {
        try {
            MimeMessage mimeMessage = prepareMimeMessageFromPedido(pedido);
            sendHtmlEmail(mimeMessage);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(pedido);
        }

    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(pedido.getCliente().getEmail());
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setSubject("Pedido confirmado! Código:" + pedido.getId());
            mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
            mimeMessageHelper.setText(htmlFromTemplatePedido(pedido), true);
            return mimeMessage;
        }

        protected String htmlFromTemplatePedido(Pedido pedido){
            Context context = new Context();
            context.setVariable("pedido", pedido);
            return templateEngine.process("email/confirmacaoPedido", context);
        }


}

