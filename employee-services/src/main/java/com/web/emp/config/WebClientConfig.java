package com.web.emp.config;

import com.web.emp.services.client.EmployeeClientService;
import com.web.emp.services.client.JsonPlaceHolderClient;
import com.web.utils.config.CommonWebClient;
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
    @Value("${login.rest.jsonPlaceHolder}")
    private String jsonPlaceHolder;

    @Bean
    public EmployeeClientService doctorClientService() {
        Map<String, String> headersMap = new HashMap<>();
        //headersMap.put(headerKey,headerValue);
        return new CommonWebClient()
                .httpServiceProxyFactory(jsonPlaceHolder, headersMap, EmployeeClientService.class);
    }

    @Bean
    public JsonPlaceHolderClient jsonPlaceHolderClient() {
        Map<String, String> headers = Map.of(
                "Authorization", "Bearer your-token",
                "Custom-Header", "CustomValue"
        );
        return new CommonWebClient()
                .httpServiceProxyFactory(jsonPlaceHolder, headers, JsonPlaceHolderClient.class);
    }
}
