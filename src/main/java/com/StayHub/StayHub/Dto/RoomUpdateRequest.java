package com.StayHub.StayHub.Dto;

import com.StayHub.StayHub.Enums.RoomType;
import com.StayHub.StayHub.entity.Hotel;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RoomUpdateRequest {

    private RoomType roomType;

    private String bedInfo;

    private String roomDescription;

    private List<String> images;

    @Positive(message = "Total count must be greater than 0")
    private Integer totalCount;

    @Positive(message = "Max capacity must be greater than 0")
    private Integer maxCapacity;

    @Positive(message = "Price per night must be greater than 0")
    private BigDecimal pricePerNight;
}
