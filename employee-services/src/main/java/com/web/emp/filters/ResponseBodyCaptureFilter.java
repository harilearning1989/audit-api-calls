package com.web.emp.filters;

import com.web.emp.wrappers.ResponseWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ResponseBodyCaptureFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ResponseWrapper wrappedResponse = new ResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, wrappedResponse);

        // After the response is written, you can capture the response body
        String responseBody = wrappedResponse.getResponseBody();
        // You can now log or store the response body
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}

