package com.web.emp.config;

import com.web.emp.services.client.EmployeeClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebClientConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebClientConfig.class);

    @Value("${login.rest.url}")
    private String loginUrl;
    @Value("${login.rest.localUrl}")
    private String loginLocalUrl;

    @Bean
    public EmployeeClientService doctorClientService() {
        Map<String, String> headersMap = new HashMap<>();
        //headersMap.put(headerKey,headerValue);
        return new CommonWebClient()
                .createClient(EmployeeClientService.class, loginLocalUrl, headersMap);
    }
}
