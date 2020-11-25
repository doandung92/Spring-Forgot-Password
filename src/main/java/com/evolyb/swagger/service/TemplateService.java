package com.evolyb.swagger.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Locale;
import java.util.Map;

@Service
public class TemplateService {
    private final SpringTemplateEngine templateEngine;

    public TemplateService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String generateTemplate(String templateName, Map<String, Object> variables) {
        Locale locale = Locale.forLanguageTag("vi");
        Context context = new Context(locale);
        if (variables != null) {
            context.setVariables(variables);
        }
        return this.templateEngine.process(templateName, context);
    }
}
