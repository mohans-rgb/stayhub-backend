package com.StayHub.StayHub.Dto;

import com.StayHub.StayHub.entity.ContactDetails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelCreateRequest {

    @NotBlank(message = "Hotel name is required")
    private String hotelName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotEmpty(message = "Amenities cannot be empty")
    private List<String> amenities;

    @NotEmpty(message = "Images cannot be empty")
    private List<String> images;

    @NotNull(message = "Contact details are required")
    private ContactDetails contactDetails;
}