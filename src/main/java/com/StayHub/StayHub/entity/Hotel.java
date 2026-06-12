package com.StayHub.StayHub.entity;


import com.StayHub.StayHub.Enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hotelName;


    @ManyToOne
    private User hotelManager;

    @Embedded
    private ContactDetails contactDetails;

    @Column(nullable = false)
    private String description;

    private String[] amenities;

    private String[] images;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
