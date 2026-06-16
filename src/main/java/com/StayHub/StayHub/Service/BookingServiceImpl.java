package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.Dto.BookingRequest;
import com.StayHub.StayHub.Dto.BookingResponseDTO;
import com.StayHub.StayHub.Enums.BookingStatus;
import com.StayHub.StayHub.Exception.BadRequestException;
import com.StayHub.StayHub.Exception.ResourceNotFoundException;
import com.StayHub.StayHub.Repository.*;
import com.StayHub.StayHub.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;
    private final GuestRepository guestRepository;
    @Override
    @Transactional
    public BookingResponseDTO initialiseBooking(BookingRequest bookingRequest ) {

        Hotel hotel= hotelRepository.findById(bookingRequest.getHotelId()).orElseThrow(() -> new ResourceNotFoundException("Hotel not found: " + bookingRequest.getHotelId()));
        Room room = roomRepository.findById(bookingRequest.getRoomId()).orElseThrow(() -> new ResourceNotFoundException("Room not found: " + bookingRequest.getRoomId()));
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Guest> guests = new ArrayList<>();

        if (bookingRequest.getGuestIds() != null &&
                !bookingRequest.getGuestIds().isEmpty()) {

            guests = guestRepository.findAllById(
                    bookingRequest.getGuestIds()
            );

            if (guests.size() != bookingRequest.getGuestIds().size()) {
                throw new ResourceNotFoundException(
                        "One or more guests not found"
                );
            }

            for (Guest guest : guests) {

                if (!Objects.equals(
                        guest.getUser().getId(),
                        user.getId())) {

                    throw new ResourceNotFoundException(
                            "Guest does not belong to current user"
                    );
                }
            }
        }
        if(!Objects.equals(room.getHotel().getId(), bookingRequest.getHotelId())){
            throw new BadRequestException(
                    "Room does not belong to the specified hotel"
            );
        }
        List<Inventory> inventories= inventoryRepository.findAndLockInventory(bookingRequest.getRoomId(),bookingRequest.getFromDate(),bookingRequest.getToDate());
        System.out.println("fromDate = " + bookingRequest.getFromDate());
        System.out.println("toDate = " + bookingRequest.getToDate());
        if(inventories.size() != ChronoUnit.DAYS.between(bookingRequest.getFromDate(),bookingRequest.getToDate())){
            throw new BadRequestException(
                    "Inventory not available for all requested dates"
            );
        }
        
        for(Inventory inventory : inventories){
            if(inventory.getClosed()== true){
                throw new BadRequestException(
                        "Room is closed for booking on selected dates"
                );
            }
            int availableRooms = inventory.getTotalCount()- inventory.getBookedCount()- inventory.getReservedCount();
            if(availableRooms< bookingRequest.getRoomsCount()){
                throw new BadRequestException(
                        "Insufficient rooms available"
                );
            }
        }

        BigDecimal amount = BigDecimal.ZERO;
        for(Inventory inventory : inventories){
            amount= amount.add(inventory.getPrice());
            inventory.setReservedCount(inventory.getReservedCount() + bookingRequest.getRoomsCount());
        }
        amount= amount.multiply(BigDecimal.valueOf(bookingRequest.getRoomsCount()));

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setHotel(hotel);
        booking.setFromDate(bookingRequest.getFromDate());
        booking.setToDate(bookingRequest.getToDate());
        booking.setRoomsCount(bookingRequest.getRoomsCount());

        booking.setTotalPrice(amount);
        booking.setBookingStatus(BookingStatus.RESERVED);
        booking.setCustomer(user);
        booking.setGuests(guests);
        booking.setExpiryTime(
                LocalDateTime.now().plusMinutes(15)
        );

        Booking savedBooking = bookingRepository.save(booking);
        inventoryRepository.saveAll(inventories);

        BookingResponseDTO response = new BookingResponseDTO();

        response.setBookingId(savedBooking.getId());
        response.setHotelName(room.getHotel().getHotelName());
        response.setRoomType(room.getRoomType().name());
        response.setFromDate(savedBooking.getFromDate());
        response.setToDate(savedBooking.getToDate());
        response.setRoomsCount(savedBooking.getRoomsCount());
        response.setTotalAmount(savedBooking.getTotalPrice());
        response.setBookingStatus(savedBooking.getBookingStatus());

        return response;

    }

    @Override
    @Transactional
    public BookingResponseDTO confirmBooking(Long bookingId) {
        Booking booking= bookingRepository.findById(bookingId).orElseThrow(()-> new ResourceNotFoundException("Booking Not found with Id " + (bookingId)));
        if(booking.getBookingStatus() != BookingStatus.RESERVED){
            throw new BadRequestException("Booking is not in RESERVED STATE");
        }
        if(booking.getExpiryTime().isBefore(LocalDateTime.now())){
            throw new BadRequestException("Booking has already EXPIRED");
        }
        List<Inventory> inventories =inventoryRepository.findAndLockInventory(booking.getRoom().getId(), booking.getFromDate(),booking.getToDate());
        for(Inventory inventory : inventories){
            inventory.setBookedCount(inventory.getBookedCount() + booking.getRoomsCount());
            inventory.setReservedCount(inventory.getReservedCount()- booking.getRoomsCount());
        }
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        inventoryRepository.saveAll(inventories);
        Booking savedBooking = bookingRepository.save(booking);
        BookingResponseDTO response = new BookingResponseDTO();

        response.setBookingId(savedBooking.getId());
        response.setHotelName(savedBooking.getHotel().getHotelName());
        response.setRoomType(savedBooking.getRoom().getRoomType().name());
        response.setFromDate(savedBooking.getFromDate());
        response.setToDate(savedBooking.getToDate());
        response.setRoomsCount(savedBooking.getRoomsCount());
        response.setTotalAmount(savedBooking.getTotalPrice());
        response.setBookingStatus(savedBooking.getBookingStatus());

        return response;
    }

    @Scheduled(fixedRate = 60000) // every 1 minute
    @Transactional
    public void expireBookings() {

        List<Booking> bookings =
                bookingRepository.findByBookingStatusAndExpiryTimeBefore(
                        BookingStatus.RESERVED,
                        LocalDateTime.now()
                );

        for (Booking booking : bookings) {

            List<Inventory> inventories =
                    inventoryRepository.findAndLockInventory(
                            booking.getRoom().getId(),
                            booking.getFromDate(),
                            booking.getToDate()
                    );

            for (Inventory inventory : inventories) {
                inventory.setReservedCount(
                        inventory.getReservedCount()
                                - booking.getRoomsCount()
                );
            }

            booking.setBookingStatus(BookingStatus.EXPIRED);

            inventoryRepository.saveAll(inventories);
            bookingRepository.save(booking);
        }
    }
    @Override
    @Transactional
    public BookingResponseDTO cancelBooking(Long bookingId) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User currentUser =
                (User) authentication.getPrincipal();

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found with id: "
                                        + bookingId));

        if (!booking.getCustomer().getId()
                .equals(currentUser.getId())) {

            throw new BadRequestException(
                    "You cannot cancel another user's booking");
        }

        if (booking.getBookingStatus()
                == BookingStatus.CANCELLED) {

            throw new BadRequestException(
                    "Booking already cancelled");
        }

        if (booking.getBookingStatus()
                == BookingStatus.EXPIRED) {

            throw new BadRequestException(
                    "Booking already expired");
        }

        List<Inventory> inventories =
                inventoryRepository.findByRoomIdAndDateBetween(
                        booking.getRoom().getId(),
                        booking.getFromDate(),
                        booking.getToDate().minusDays(1)
                );

        if (booking.getBookingStatus()
                == BookingStatus.RESERVED) {

            for (Inventory inventory : inventories) {

                inventory.setReservedCount(
                        inventory.getReservedCount()
                                - booking.getRoomsCount());
            }
        }

        if (booking.getBookingStatus()
                == BookingStatus.CONFIRMED) {

            for (Inventory inventory : inventories) {

                inventory.setBookedCount(
                        inventory.getBookedCount()
                                - booking.getRoomsCount());
            }
        }

        inventoryRepository.saveAll(inventories);

        booking.setBookingStatus(
                BookingStatus.CANCELLED);

        bookingRepository.save(booking);

        return modelMapper.map(
                booking,
                BookingResponseDTO.class);
    }
    @Override
    public List<BookingResponseDTO> getMyBookings() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        List<Booking> bookings =
                bookingRepository.findAllByCustomerId(user.getId());

        return bookings.stream()
                .map(this::mapToBookingResponse)
                .toList();
    }


    @Override
    public BookingResponseDTO getBookingById(Long bookingId) {

        Booking booking =
                bookingRepository.findById(bookingId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Booking not found with id: " + bookingId
                                ));

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        if (!Objects.equals(
                booking.getCustomer().getId(),
                user.getId())) {

            throw new ResourceNotFoundException(
                    "Booking does not belong to current user"
            );
        }

        return mapToBookingResponse(booking);
    }



    private BookingResponseDTO mapToBookingResponse(Booking booking) {

        BookingResponseDTO response =
                new BookingResponseDTO();

        response.setBookingId(booking.getId());
        response.setHotelName(
                booking.getHotel().getHotelName()
        );
        response.setRoomType(
                booking.getRoom().getRoomType().name()
        );
        response.setFromDate(
                booking.getFromDate()
        );
        response.setToDate(
                booking.getToDate()
        );
        response.setRoomsCount(
                booking.getRoomsCount()
        );
        response.setTotalAmount(
                booking.getTotalPrice()
        );
        response.setBookingStatus(
                booking.getBookingStatus()
        );

        return response;
    }


}
