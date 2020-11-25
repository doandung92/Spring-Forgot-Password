package com.evolyb.swagger.controller;

import com.evolyb.swagger.service.TemplateService;
import com.evolyb.swagger.vm.MailRequest;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {
    private final TemplateService templateService;
    Environment env;

    public MainController(TemplateService templateService, Environment env) {
        this.templateService = templateService;
        this.env = env;
    }

    @GetMapping("/thymeleaf")
    public String generateTemplate(MailRequest mailRequest){
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", mailRequest.getName());
        variables.put("class", mailRequest.getClazz());
        String templateSourcePath = env.getProperty("mail.demo");
        return templateService.generateTemplate(templateSourcePath,variables);
    }
}
