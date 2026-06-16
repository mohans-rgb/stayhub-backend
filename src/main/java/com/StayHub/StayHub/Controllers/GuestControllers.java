package com.StayHub.StayHub.Controllers;

import com.StayHub.StayHub.Dto.GuestCreateRequest;
import com.StayHub.StayHub.Dto.GuestCreatedResponse;
import com.StayHub.StayHub.Service.GuestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guests")
@RequiredArgsConstructor
public class GuestControllers {

    private final GuestService guestService;


    @PostMapping
    public GuestCreatedResponse createGuest(
            @RequestBody @Valid GuestCreateRequest guestCreateRequest) {

        return guestService.createGuest(guestCreateRequest);
    }

    @GetMapping
    public List<GuestCreatedResponse> getAllGuests() {

        return guestService.getAllGuests();
    }

    @GetMapping("/{guestId}")
    public GuestCreatedResponse getGuestById(
            @PathVariable Long guestId) {

        return guestService.getGuestById(guestId);
    }

    @DeleteMapping("/{guestId}")
    public void deleteGuestById(
            @PathVariable Long guestId) {

        guestService.deleteGuestById(guestId);
    }
}