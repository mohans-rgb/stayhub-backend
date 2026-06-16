package com.StayHub.StayHub.Dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class InventoryUpdateRequest {

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Closed status is required")
    private Boolean closed;

    @NotNull(message = "Total count is required")
    @PositiveOrZero(message = "Total count cannot be negative")
    private Integer totalCount;
}