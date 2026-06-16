package com.StayHub.StayHub.entity;


import com.StayHub.StayHub.Enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String guestName;

    @Column(nullable = false)
    private String guestEmail;

    @Column(nullable = false)
    private Integer guestAge;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender guestGender;

    @ManyToOne
    private User user;

}
