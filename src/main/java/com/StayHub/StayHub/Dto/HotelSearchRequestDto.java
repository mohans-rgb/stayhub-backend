package com.StayHub.StayHub.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HotelSearchRequestDto {

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "From date is required")
    private LocalDate fromDate;

    @NotNull(message = "To date is required")
    private LocalDate toDate;

    @NotNull(message = "Rooms count is required")
    @Positive(message = "Rooms count must be greater than 0")
    private Integer roomsCount;
}