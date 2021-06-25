package com.example.demo.junioroffers.infrastructure.offer.client;

import com.example.demo.junioroffers.infrastructure.RemoteOfferClient;
import com.example.demo.junioroffers.infrastructure.offer.dto.OfferDto;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.message.ParameterizedMessageFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class OfferHttpClient implements RemoteOfferClient {

    private  final RestTemplate restTemplate;
    private final String url;

    @Override
    public List<OfferDto> getOffers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(httpHeaders);

        try{
            ResponseEntity<List<OfferDto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
                    new ParameterizedTypeReference<List<OfferDto>>() {}); //mapowanie requestu na listę ofert
            final List<OfferDto> body = responseEntity.getBody();
            return (body != null) ? body : Collections.emptyList();
         }catch (ResourceAccessException e){
            return Collections.emptyList();
        }
    }
}
