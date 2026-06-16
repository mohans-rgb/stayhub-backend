package com.StayHub.StayHub.entity;


import com.StayHub.StayHub.Enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    @Embedded
    private ContactDetails contactDetails;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    private List<String> amenities;

    @ElementCollection
    private List<String> images;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
