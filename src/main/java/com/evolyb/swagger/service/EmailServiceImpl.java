package com.evolyb.swagger.service;

import com.evolyb.swagger.vm.MailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl {
    @Qualifier("getJavaMailSender")
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private Environment env;

    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        message.setContent(text,"text/html; charset=utf-8");
        helper.setTo(to);
        helper.setSubject(subject);
        emailSender.send(message);
        this.emailSender.send(message);
    }
    public String generateTemplate(MailRequest mailRequest){
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", mailRequest.getName());
        variables.put("class", mailRequest.getClazz());
        String code = Math.round(Math.random()*1000)+"";
        variables.put("code", code);
        String templateSourcePath = env.getProperty("mail.resetPassword");
        return templateService.generateTemplate(templateSourcePath,variables);
    }
}
