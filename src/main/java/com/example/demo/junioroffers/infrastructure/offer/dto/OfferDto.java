package com.example.demo.junioroffers.infrastructure.offer.dto;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {

    String title;
    String company;
    String salary;
    String offerUrl;

}
