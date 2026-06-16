package com.StayHub.StayHub.entity;

import com.StayHub.StayHub.Enums.RoomType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Hotel hotel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType roomType;


    @Column(nullable = false)
    private String bedInfo;

    @Column(nullable = false)
    private String roomDescription;

    private List<String> images;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private  Integer totalCount;

    @Column(nullable = false)
    private Integer maxCapacity;

    @Column(nullable = false)
    private BigDecimal pricePerNight;

}
