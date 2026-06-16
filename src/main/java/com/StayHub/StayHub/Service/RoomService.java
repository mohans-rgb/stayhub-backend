package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.Dto.RoomCreateRequest;
import com.StayHub.StayHub.Dto.RoomResponseDto;
import com.StayHub.StayHub.Dto.RoomUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoomService {
    RoomResponseDto createRoom(Long id, RoomCreateRequest roomCreateRequest);
    RoomResponseDto getRoom(Long id,Long hotelId);
    Page<RoomResponseDto> getAllRooms(Long hotelId, Pageable pageable);
    RoomResponseDto updateRoomById(Long hotelId, Long roomId, RoomUpdateRequest roomUpdateRequest);
    void deleteRoomById(Long hotelId, Long roomId);

}
