package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.DTO.GuestDto;

import com.StayHub.StayHub.entity.Guest;

import java.util.List;

public interface GuestService {


    List<GuestDto> getAllGuests();

    void updateGuest(Long guestId, GuestDto guestDto);

    void deleteGuest(Long guestId);

    GuestDto addNewGuest(GuestDto guestDto);

}
