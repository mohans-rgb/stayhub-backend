package com.StayHub.StayHub.Dto;

import com.StayHub.StayHub.Enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookingResponseDTO {

    private Long bookingId;

    private String hotelName;

    private String roomType;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Integer roomsCount;

    private BigDecimal totalAmount;

    private BookingStatus bookingStatus;

}
