package com.StayHub.StayHub.Dto;

import com.StayHub.StayHub.Enums.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
public class RoomResponseDto {

    private Long id;

    private Long hotelId;

    private List<String> images;

    private String roomDescription;

    private RoomType roomType;

    private String bedInfo;

    private Integer totalCount;

    private BigDecimal pricePerNight;

    private  Integer maxCapacity;
}
