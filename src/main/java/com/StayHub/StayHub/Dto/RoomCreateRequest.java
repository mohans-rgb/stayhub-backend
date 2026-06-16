package com.StayHub.StayHub.Dto;

import com.StayHub.StayHub.Enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class RoomCreateRequest {

    @NotEmpty(message = "Images cannot be empty")
    private List<String> images;

    @NotBlank(message = "Room description is required")
    private String roomDescription;

    @NotNull(message = "Room type is required")
    private RoomType roomType;

    @NotBlank(message = "Bed info is required")
    private String bedInfo;

    @NotNull(message = "Total count is required")
    @Positive(message = "Total count must be greater than 0")
    private Integer totalCount;

    @NotNull(message = "Price per night is required")
    @Positive(message = "Price per night must be greater than 0")
    private BigDecimal pricePerNight;

    @NotNull(message = "Max capacity is required")
    @Positive(message = "Max capacity must be greater than 0")
    private Integer maxCapacity;
}