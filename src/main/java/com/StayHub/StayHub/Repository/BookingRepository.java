package com.StayHub.StayHub.Repository;

import com.StayHub.StayHub.Enums.BookingStatus;
import com.StayHub.StayHub.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByBookingStatusAndExpiryTimeBefore(BookingStatus bookingStatus , LocalDateTime time);
    List<Booking> findAllByCustomerId(Long customerId);
}
