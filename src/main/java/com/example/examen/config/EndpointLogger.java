package com.example.examen.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointLogger implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(EndpointLogger.class);
    private final RequestMappingHandlerMapping mapping;

    public EndpointLogger(RequestMappingHandlerMapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public void run(String... args) throws Exception {
        mapping.getHandlerMethods().forEach((info, method) -> {
            RequestMappingInfo req = info;
            log.info("Mapped URL: {} --> {}#{}", req.getPatternsCondition(), method.getBeanType().getSimpleName(), method.getMethod().getName());
        });
    }
}
