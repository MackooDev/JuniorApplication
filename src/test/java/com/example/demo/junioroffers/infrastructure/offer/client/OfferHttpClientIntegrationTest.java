package com.example.demo.junioroffers.infrastructure.offer.client;

import com.example.demo.junioroffers.config.OfferHttpClientTestConfig;
import com.example.demo.junioroffers.infrastructure.RemoteOfferClient;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Fault;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.SocketUtils;
import wiremock.org.apache.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.InstanceOfAssertFactories.predicate;

public class OfferHttpClientIntegrationTest implements SampleOfferDto {

    int port = SocketUtils.findAvailableTcpPort();

    WireMockServer wireMocServer;

    RemoteOfferClient remoteOfferClient = new OfferHttpClientTestConfig().remoteOfferTestClient("http://;localhost:" + port + "/offers", 1000, 1000);

    @BeforeEach
    void setup(){
        wireMocServer = new WireMockServer(options().port(port));
        wireMocServer.start();
        WireMock.configureFor(port);
    }

    @AfterEach
    void tearDown() {wireMocServer.stop(); }

    @Test
    void should_return_two_job_offers(){

        //given
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithTwoOffersJson())));

        //when
        //then

    }



    private String bodyWithTwoOffersJson() {
        return "[{\n" +
                "    \"title\": \"Software Engineer - Mobile (m/f/d)\",\n" +
                "    \"company\": \"Cybersource\",\n" +
                "    \"salary\": \"4k - 8k PLN\",\n" +
                "    \"offerUrl\": \"https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Junior DevOps Engineer\",\n" +
                "    \"company\": \"CDQ Poland\",\n" +
                "    \"salary\": \"8k - 14k PLN\",\n" +
                "    \"offerUrl\": \"https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd\"\n" +
                "  }]";
    }




}
