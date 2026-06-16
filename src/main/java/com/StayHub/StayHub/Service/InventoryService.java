package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.Dto.HotelResponseDto;
import com.StayHub.StayHub.Dto.HotelSearchResponseDto;
import com.StayHub.StayHub.Dto.InventoryResponse;
import com.StayHub.StayHub.Dto.InventoryUpdateRequest;
import com.StayHub.StayHub.entity.Hotel;
import com.StayHub.StayHub.entity.Inventory;
import com.StayHub.StayHub.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface InventoryService {

     void generateInventoryForRoom(Room room);

     List<InventoryResponse> findByRoomIdAndDateBetween(Long roomId, LocalDate startDate, LocalDate endDate);
     List<InventoryResponse> updateInventoryById(Long roomId, LocalDate startDate , LocalDate endDate, InventoryUpdateRequest inventoryUpdateRequest);

     //Page<HotelResponseDto> searchHotel(String city, LocalDate fromDate, LocalDate toDate, Integer roomCount, Pageable pageable);
     Page<HotelSearchResponseDto> searchHotel(
             String city,
             LocalDate fromDate,
             LocalDate toDate,
             Integer roomCount,
             Pageable pageable
     );
}
