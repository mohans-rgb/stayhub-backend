package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.Dto.RoomCreateRequest;
import com.StayHub.StayHub.Dto.RoomResponseDto;
import com.StayHub.StayHub.Dto.RoomUpdateRequest;
import com.StayHub.StayHub.Exception.ResourceNotFoundException;
import com.StayHub.StayHub.Repository.HotelRepository;
import com.StayHub.StayHub.Repository.RoomRepository;
import com.StayHub.StayHub.entity.Hotel;
import com.StayHub.StayHub.entity.Room;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    @Override
    public RoomResponseDto createRoom(Long id, RoomCreateRequest roomCreateRequest) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id :" +id));
        Room room = modelMapper.map(roomCreateRequest,Room.class);
        //room.setHotel(hotelRepository.findById(id).orElseThrow(()->new ("Hotel not found with id:"+id)));
        room.setHotel(hotel);
        Room savedroom = roomRepository.save(room);
        inventoryService.generateInventoryForRoom(savedroom);
        return modelMapper.map(savedroom,RoomResponseDto.class);
    }

    @Override
    public RoomResponseDto getRoom(Long id, Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id:" +hotelId));
        Room room = roomRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Room not found with id:" +id));
        if(!Objects.equals(room.getHotel().getId(), hotelId)){
            throw new ResourceNotFoundException("Room doesnt belong to the hotel");
        }
        return modelMapper.map(room,RoomResponseDto.class);
    }

    @Override
    public Page<RoomResponseDto> getAllRooms(Long hotelId, Pageable pageable) {
        Page<Room> rooms = roomRepository.findAllByHotelId(hotelId,pageable);
        return rooms.map(room -> modelMapper.map(room,RoomResponseDto.class));
    }

    @Override
    public RoomResponseDto updateRoomById(Long hotelId, Long roomId, RoomUpdateRequest roomUpdateRequest) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id:" +hotelId));
        Room room = roomRepository.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("Room not found with id:" +roomId));
        if(!Objects.equals(room.getHotel().getId(), hotelId)){
            throw new ResourceNotFoundException("Room doesnt belong to the hotel");
        }

        modelMapper.map(roomUpdateRequest,room);

        Room savedRoom = roomRepository.save(room);

        return modelMapper.map(savedRoom,RoomResponseDto.class);

    }

    @Override
    public void deleteRoomById(Long hotelId, Long roomId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id:" +hotelId));
        Room room = roomRepository.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("Room not found with id:" +roomId));
        if(!Objects.equals(room.getHotel().getId(), hotelId)){
            throw new ResourceNotFoundException("Room doesnt belong to the hotel");
        }
        roomRepository.deleteById(roomId);

    }





}
