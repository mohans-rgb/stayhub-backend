package com.StayHub.StayHub.Controllers;

import com.StayHub.StayHub.Dto.HotelSearchResponseDto;
import com.StayHub.StayHub.Dto.InventoryResponse;
import com.StayHub.StayHub.Dto.InventoryUpdateRequest;
import com.StayHub.StayHub.Repository.InventoryRepository;
import com.StayHub.StayHub.Service.InventoryService;
import com.StayHub.StayHub.entity.Hotel;
import com.StayHub.StayHub.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms/{roomId}/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;
    private final InventoryRepository inventoryRepository;

    @GetMapping
    public List<InventoryResponse> getInventory(@PathVariable Long roomId, @RequestParam LocalDate startDate , @RequestParam LocalDate endDate){
        return inventoryService.findByRoomIdAndDateBetween(roomId,startDate,endDate);
    }
    @PutMapping
    public List<InventoryResponse> updateInventory(@PathVariable Long roomId, @RequestParam LocalDate startDate , @RequestParam LocalDate endDate , @RequestBody InventoryUpdateRequest inventoryUpdateRequest){
        return inventoryService.updateInventoryById(roomId, startDate, endDate, inventoryUpdateRequest);


    }
    @GetMapping("/search")
    public Page<HotelSearchResponseDto> searchHotel(
            @RequestParam String city,
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate,
            @RequestParam Integer roomCount,
            Pageable pageable) {

        return inventoryService.searchHotel(
                city,
                fromDate,
                toDate,
                roomCount,
                pageable
        );
    }
}
