package com.web.emp.wrappers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestWrapper extends HttpServletRequestWrapper {

    private final String requestBody;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        this.requestBody = stringBuilder.toString();
    }

    public String getRequestBody() {
        return requestBody;
    }
}

