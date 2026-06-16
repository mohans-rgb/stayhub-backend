package com.StayHub.StayHub.Service;


import com.StayHub.StayHub.Dto.HotelResponseDto;
import com.StayHub.StayHub.Dto.HotelSearchResponseDto;
import com.StayHub.StayHub.Dto.InventoryResponse;
import com.StayHub.StayHub.Dto.InventoryUpdateRequest;
import com.StayHub.StayHub.Repository.InventoryRepository;
import com.StayHub.StayHub.Repository.RoomRepository;
import com.StayHub.StayHub.entity.Hotel;
import com.StayHub.StayHub.entity.Inventory;
import com.StayHub.StayHub.entity.Room;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public void generateInventoryForRoom(Room room) {
        List<Inventory> inventories =new ArrayList<>();

        LocalDate today = LocalDate.now();

        for(int i=0;i<365;i++){
            LocalDate inventoryDate = today.plusDays(i);

            Inventory inventory= new Inventory();
            inventory.setRoom(room);
            inventory.setClosed(false);
            inventory.setBookedCount(0);
            inventory.setReservedCount(0);
            inventory.setDate(inventoryDate);
            inventory.setTotalCount(room.getTotalCount());
            inventory.setPrice(room.getPricePerNight());

            inventories.add(inventory);
        }
        inventoryRepository.saveAll(inventories);

    }

    @Override
    public List<InventoryResponse> findByRoomIdAndDateBetween(Long roomId, LocalDate startDate, LocalDate endDate) {
        List<Inventory> inventory =  inventoryRepository.findByRoomIdAndDateBetween(roomId, startDate, endDate);
        return inventory.stream().map(inventory1-> modelMapper.map(inventory1, InventoryResponse.class)).toList();
    }

    @Override
    public List<InventoryResponse> updateInventoryById(Long roomId, LocalDate startDate, LocalDate endDate, InventoryUpdateRequest inventoryUpdateRequest) {
        System.out.println("Update inventory called");
        List<Inventory> inventories = inventoryRepository.findByRoomIdAndDateBetween(roomId, startDate, endDate);
        inventories.forEach(inventory -> modelMapper.map(inventoryUpdateRequest,inventory));
        System.out.println(inventoryUpdateRequest.getPrice());
        System.out.println(inventoryUpdateRequest.getClosed());
        List<Inventory> updatedInventory = inventoryRepository.saveAll(inventories);
        return updatedInventory.stream().map(inventory -> modelMapper.map(inventory,InventoryResponse.class)).toList();

    }

    @Override
    public Page<HotelSearchResponseDto> searchHotel(
            String city,
            LocalDate fromDate,
            LocalDate toDate,
            Integer roomCount,
            Pageable pageable) {
        //throw new RuntimeException("I AM INSIDE SEARCH HOTEL");


        Long nights = ChronoUnit.DAYS.between(fromDate, toDate);

        Page<Hotel> hotels = inventoryRepository.searchHotel(
                city,
                fromDate,
                toDate,
                roomCount,
                nights,
                pageable
        );

        return hotels.map(hotel -> {

            HotelSearchResponseDto dto =
                    modelMapper.map(
                            hotel,
                            HotelSearchResponseDto.class
                    );
            System.out.println("Hotel = " + hotel.getHotelName());
            System.out.println("Rooms = " + hotel.getRooms());

            if (hotel.getRooms() != null) {
                System.out.println("Room count = " + hotel.getRooms().size());
            } else {
                System.out.println("Rooms is NULL");
            }

            dto.setMinPrice(
                    hotel.getRooms()
                            .stream()
                            .map(Room::getPricePerNight)
                            .min(BigDecimal::compareTo)
                            .orElse(null)
            );

            return dto;
        });
    }
}
