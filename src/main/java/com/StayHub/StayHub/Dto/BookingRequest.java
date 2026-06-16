package com.StayHub.StayHub.Dto;

import com.StayHub.StayHub.entity.Hotel;
import com.StayHub.StayHub.entity.Room;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class BookingRequest {

    @NotNull(message = "Hotel id is required")
    private Long hotelId;

    @NotNull(message = "Room id is required")
    private Long roomId;

    @NotNull(message = "From date is required")
    private LocalDate fromDate;

    @NotNull(message = "To date is required")
    private LocalDate toDate;


    private List<Long> guestIds;

    @NotNull(message = "Rooms count is required")
    @Positive(message = "Rooms count must be greater than 0")
    private Integer roomsCount;



}