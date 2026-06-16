package com.StayHub.StayHub.Controllers;


import com.StayHub.StayHub.Dto.RoomCreateRequest;
import com.StayHub.StayHub.Dto.RoomResponseDto;
import com.StayHub.StayHub.Dto.RoomUpdateRequest;
import com.StayHub.StayHub.Service.RoomService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels/{hotelId}/rooms")
public class RoomControllers {

    private final RoomService roomService;

    @PostMapping
    public RoomResponseDto createRoom(@PathVariable Long hotelId , @Valid @RequestBody RoomCreateRequest roomCreateRequest){
        return roomService.createRoom(hotelId,roomCreateRequest);
    }

    @GetMapping("/{roomId}")
    public RoomResponseDto getRoom(@PathVariable Long roomId, @PathVariable Long hotelId){
        return roomService.getRoom(roomId,hotelId);
    }

    @GetMapping
    public Page<RoomResponseDto> getAllRooms(@PathVariable Long hotelId, @RequestParam Integer page , @RequestParam Integer size){
        Pageable pageable =  PageRequest.of(page,size);
        return roomService.getAllRooms(hotelId,pageable);
    }

    @PutMapping("/{roomId}")
    public RoomResponseDto updateRoomById(@PathVariable Long hotelId , @PathVariable Long roomId, @Valid @RequestBody RoomUpdateRequest roomUpdateRequest){
        return roomService.updateRoomById(hotelId,roomId,roomUpdateRequest);
    }

    @DeleteMapping("/{roomId}")
    public void deleteRoomById(@PathVariable Long hotelId , @PathVariable Long roomId){
         roomService.deleteRoomById(hotelId,roomId);
    }





}
