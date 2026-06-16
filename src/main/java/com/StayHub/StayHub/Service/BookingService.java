package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.Dto.BookingRequest;
import com.StayHub.StayHub.Dto.BookingResponseDTO;

import java.util.List;

public interface BookingService {

    BookingResponseDTO initialiseBooking(BookingRequest bookingRequest);
    BookingResponseDTO confirmBooking(Long bookingId);

    List<BookingResponseDTO> getMyBookings();

    BookingResponseDTO getBookingById(Long bookingId);
    BookingResponseDTO cancelBooking(Long bookingId);
}
