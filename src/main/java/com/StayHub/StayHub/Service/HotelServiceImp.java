package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.Dto.HotelCreateRequest;
import com.StayHub.StayHub.Dto.HotelResponseDto;
import com.StayHub.StayHub.Exception.ResourceNotFoundException;
import com.StayHub.StayHub.Repository.HotelRepository;
import com.StayHub.StayHub.entity.Hotel;
import com.StayHub.StayHub.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImp implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    public HotelResponseDto createHotel(HotelCreateRequest hotelCreateRequest){

        System.out.println("CONTACT DETAILS = " + hotelCreateRequest.getContactDetails());

        if(hotelCreateRequest.getContactDetails() != null){
            System.out.println("CITY = " +
                    hotelCreateRequest.getContactDetails().getCity());
        }

        Hotel hotel = modelMapper.map(hotelCreateRequest, Hotel.class);

        System.out.println("MAPPED CONTACT DETAILS = " + hotel.getContactDetails());

        if(hotel.getContactDetails() != null){
            System.out.println("MAPPED CITY = " +
                    hotel.getContactDetails().getCity());
        }

        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        hotel.setHotelManager(currentUser);

        Hotel savedHotel = hotelRepository.save(hotel);

        return modelMapper.map(savedHotel, HotelResponseDto.class);
    }

    @Override
    public HotelResponseDto getHotel(Long id) {
       Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id :" + id));
       return modelMapper.map(hotel,HotelResponseDto.class);
    }

    @Override
    public Page<HotelResponseDto> getAllHotels(Pageable pageable) {
        Page<Hotel> hotels = hotelRepository.findAll(pageable);
        return hotels.map(hotel -> modelMapper.map(hotel,HotelResponseDto.class));

    }

}
