package com.web.emp.wrappers;

import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.ServletOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ServletOutputStream servletOutputStream = new ServletOutputStream() {

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b); // Write captured bytes to the ByteArrayOutputStream
        }
    };

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return servletOutputStream;  // Return the custom ServletOutputStream
    }

    public String getResponseBody() {
        return outputStream.toString();  // Convert captured bytes to string
    }

    @Override
    public void flushBuffer() throws IOException {
        super.flushBuffer(); // Ensure the response is flushed
    }
}




