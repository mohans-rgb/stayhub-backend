package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.DTO.HotelPriceResponseDto;
import com.StayHub.StayHub.DTO.HotelSearchRequest;
import com.StayHub.StayHub.DTO.InventoryDto;
import com.StayHub.StayHub.DTO.UpdateInventoryRequestDto;

import com.StayHub.StayHub.entity.Hotel;
import com.StayHub.StayHub.entity.Inventory;
import com.StayHub.StayHub.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface InventoryService {


     void initializeRoomForAYear(Room room);

     void deleteAllInventories(Room room);

     Page<HotelPriceResponseDto> searchHotels(HotelSearchRequest hotelSearchRequest);

     List<InventoryDto> getAllInventoryByRoom(Long roomId);

     void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto);

}
