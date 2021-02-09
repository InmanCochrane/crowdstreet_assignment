package com.crowdstreet.assignment.client;

import com.crowdstreet.assignment.data.model.CallbackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExampleClient {
    public static final String API_BASE = "https://www.example.com";
    private final RestTemplate restTemplate;

    @Autowired
    public ExampleClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void performCallbackRequest(CallbackRequest callbackRequest) {
        restTemplate.postForEntity(String.format("%s/request", API_BASE),
                callbackRequest, Object.class);
    }
}
