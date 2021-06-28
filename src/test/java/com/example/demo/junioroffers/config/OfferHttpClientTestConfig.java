package com.example.demo.junioroffers.config;

import com.example.demo.junioroffers.infrastructure.RemoteOfferClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OfferHttpClientTestConfig extends Config{

    private RemoteOfferClient remoteOfferClient(String url, int connectionTimeout, int readTimeout) {

        final RestTemplate restTemplate = restTemplate(connectionTimeout, readTimeout, restTemplateResponseErrorHandler());
        return offerHttpClient(restTemplate, url);

    }

}
