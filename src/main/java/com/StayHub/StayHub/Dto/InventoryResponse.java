package com.StayHub.StayHub.Dto;

import com.StayHub.StayHub.entity.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryResponse {

    private Long id;

    private Long roomId;

    private Room room;

    private LocalDate date;

    private BigDecimal price;

    private Boolean closed;

    private Integer totalCount;

    private Integer bookedCount;

    private Integer reservedCount;

    private Integer availableRooms;

    private Boolean available;

}

