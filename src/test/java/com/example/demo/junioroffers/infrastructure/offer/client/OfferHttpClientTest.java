package com.example.demo.junioroffers.infrastructure.offer.client;

import com.example.demo.junioroffers.infrastructure.offer.dto.OfferDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.hibernate.mapping.Collection;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class OfferHttpClientTest {



    @Test
    public void should_return_one_element_list_of_offer(){
        // given
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        String url = "test";

        when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<OfferDto>>>any()))
                .thenReturn(new ResponseEntity<>(Collections.singletonList(new OfferDto()), HttpStatus.ACCEPTED));
        OfferHttpClient offerHttpClient = new OfferHttpClient(restTemplate, url);
        //when
        List<OfferDto> offers = offerHttpClient.getOffers();
        //then
        assertThat(offers.size()).isEqualTo(1);
    }


}