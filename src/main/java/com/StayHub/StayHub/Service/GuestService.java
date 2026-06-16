package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.Dto.GuestCreateRequest;
import com.StayHub.StayHub.Dto.GuestCreatedResponse;
import com.StayHub.StayHub.entity.Guest;

import java.util.List;

public interface GuestService {

    GuestCreatedResponse createGuest(GuestCreateRequest guestCreateRequest);

    List<GuestCreatedResponse> getAllGuests();

    GuestCreatedResponse getGuestById(Long guestId);

    void deleteGuestById(Long guestId);
}
