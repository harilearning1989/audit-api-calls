package com.web.emp.filters;

import com.web.emp.wrappers.RequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestBodyCaptureFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        RequestWrapper wrappedRequest = new RequestWrapper((HttpServletRequest) request);
        chain.doFilter(wrappedRequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}

