package com.StayHub.StayHub.Controllers;

import com.StayHub.StayHub.Dto.BookingRequest;
import com.StayHub.StayHub.Dto.BookingResponseDTO;
import com.StayHub.StayHub.Service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    @PostMapping("/init")
    public ResponseEntity<BookingResponseDTO> initialiseBooking(
            @RequestBody @Valid  BookingRequest bookingRequest
    ) {

        BookingResponseDTO response =
                bookingService.initialiseBooking(bookingRequest);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/{bookingId}/confirm")
    public ResponseEntity<BookingResponseDTO> confirmBooking(
            @PathVariable Long bookingId){
        BookingResponseDTO response =
                bookingService.confirmBooking(bookingId);

        return ResponseEntity.ok(response);
    }
    @GetMapping
    public List<BookingResponseDTO> getMyBookings() {
        return bookingService.getMyBookings();
    }

    @GetMapping("/{bookingId}")
    public BookingResponseDTO getBookingById(
            @PathVariable Long bookingId) {

        return bookingService.getBookingById(bookingId);
    }
    @PostMapping("/{bookingId}/cancel")
    public ResponseEntity<BookingResponseDTO> cancelBooking(
            @PathVariable Long bookingId){

        BookingResponseDTO response =
                bookingService.cancelBooking(bookingId);

        return ResponseEntity.ok(response);
    }
}