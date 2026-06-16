package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.Dto.HotelCreateRequest;
import com.StayHub.StayHub.Dto.HotelResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {
    HotelResponseDto createHotel(HotelCreateRequest hotelCreateRequest);
    HotelResponseDto getHotel(Long id);
    Page<HotelResponseDto> getAllHotels(Pageable pageable);
}
