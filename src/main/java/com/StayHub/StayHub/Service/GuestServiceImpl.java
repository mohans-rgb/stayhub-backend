package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.Dto.GuestCreateRequest;
import com.StayHub.StayHub.Dto.GuestCreatedResponse;
import com.StayHub.StayHub.Exception.BadRequestException;
import com.StayHub.StayHub.Exception.ResourceNotFoundException;
import com.StayHub.StayHub.Repository.GuestRepository;
import com.StayHub.StayHub.entity.Guest;
import com.StayHub.StayHub.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService{

    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;


    @Override
    public GuestCreatedResponse createGuest(GuestCreateRequest guestCreateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Guest guest = modelMapper.map(guestCreateRequest,Guest.class);
         guest.setUser(user);
        Guest savedGuest= guestRepository.save(guest);
        return modelMapper.map(savedGuest,GuestCreatedResponse.class);
    }

    @Override
    public List<GuestCreatedResponse> getAllGuests() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<Guest> guests = guestRepository.findAllGuestsByUserId(user.getId());
        return guests.stream().map(guest -> modelMapper.map(guest,GuestCreatedResponse.class)).toList();

    }

    @Override
    public GuestCreatedResponse getGuestById(Long guestId) {
        Guest guest = guestRepository.findById(guestId).orElseThrow(()-> new ResourceNotFoundException("Guest Id not found with id :"+ guestId));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if(!Objects.equals(guest.getUser().getId(), user.getId())){
            throw  new ResourceNotFoundException("Guest doesnt belong to User");
        }
        return modelMapper.map(guest,GuestCreatedResponse.class);
    }

    @Override
    public void deleteGuestById(Long guestId) {
        Guest guest = guestRepository.findById(guestId).orElseThrow(()-> new ResourceNotFoundException("Guest Id not found with id :"+ guestId));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if(!Objects.equals(guest.getUser().getId(), user.getId())){
            throw  new ResourceNotFoundException("Guest doesnt belong to User");
        }
        guestRepository.delete(guest);


    }
}
