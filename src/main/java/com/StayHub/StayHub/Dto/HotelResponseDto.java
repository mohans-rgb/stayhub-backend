package com.StayHub.StayHub.Dto;

import com.StayHub.StayHub.entity.ContactDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class HotelResponseDto {

    private Long id;

    private String hotelName;

    private String description;

    private List<String> amenities;

    private List<String> images;

    private ContactDetails contactDetails;
}
