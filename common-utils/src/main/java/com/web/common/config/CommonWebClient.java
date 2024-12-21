package com.web.common.config;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class CommonWebClient {

    // Generic method to create a WebClient for any service client
    public <T> T createClient(Class<T> clientClass, String baseUrl, Map<String, String> headersMap) {
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl) // set the base URL for the WebClient
                .defaultHeaders(headers -> headersMap.forEach(headers::add)) // add headers to the WebClient
                .build();

        // Create a proxy client for the provided clientClass (service interface)
        return webClient.get()
                .uri(baseUrl) // Specify the uri here or pass a specific endpoint if needed
                .retrieve() // Perform the GET request (you can customize this further for other HTTP methods)
                .bodyToMono(clientClass) // Map the response body to the class type
                .block(); // Wait for the response to complete (block to get the result)
    }
}
