package com.StayHub.StayHub.Dto;

import com.StayHub.StayHub.entity.ContactDetails;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class HotelSearchResponseDto {

    private  Long id;

    private String hotelName;

    private BigDecimal minPrice;

    private List<String> images;

}
