package com.evolyb.swagger.controller;

import com.evolyb.swagger.service.EmailServiceImpl;
import com.evolyb.swagger.vm.MailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/mail/")
public class MailController {
    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendEmail(@RequestBody MailRequest mailRequest) throws MessagingException {
        String to = mailRequest.getSendTo();
        String subject = "Forgot Password";
        String content = emailService.generateTemplate(mailRequest);
        emailService.sendSimpleMessage(to,subject,content);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

