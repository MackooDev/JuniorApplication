package com.example.demo.junioroffers.infrastructure;

import com.example.demo.junioroffers.infrastructure.offer.dto.OfferDto;

import java.util.List;

public interface RemoteOfferClient {

    List<OfferDto> getOffers();

}
