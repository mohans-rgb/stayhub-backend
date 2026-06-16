package com.StayHub.StayHub.Controllers;


import com.StayHub.StayHub.Dto.HotelCreateRequest;
import com.StayHub.StayHub.Dto.HotelResponseDto;
import com.StayHub.StayHub.Service.HotelService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelControllers {

    private final HotelService hotelService;
    private final ModelMapper modelMapper;

    @PostMapping
    public HotelResponseDto createHotel(
            @Valid @RequestBody HotelCreateRequest request) {

        System.out.println(request);
        System.out.println(request.getContactDetails());

        return hotelService.createHotel(request);
    }
    @GetMapping("/{id}")
    public HotelResponseDto getHotel(@PathVariable Long id){
        return hotelService.getHotel(id);
    }

    @GetMapping
    public Page<HotelResponseDto> getAllHotels(@RequestParam Integer page , @RequestParam Integer size){
        Pageable pageable= PageRequest.of(page,size);
        return hotelService.getAllHotels(pageable);
    }


}
