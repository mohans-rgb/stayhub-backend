package com.StayHub.StayHub.entity;

import com.StayHub.StayHub.Enums.RoomType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    private String[] images;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Integer maxCapacity;

}
