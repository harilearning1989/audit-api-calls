package com.web.emp.config;

import com.web.emp.interceptors.ServiceAuditInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ServiceAuditInterceptor serviceAuditInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serviceAuditInterceptor)
                .addPathPatterns("/**");  // Apply the interceptor to all endpoints or specific ones
    }
}
