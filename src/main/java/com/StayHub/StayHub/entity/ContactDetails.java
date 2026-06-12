package com.StayHub.StayHub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ContactDetails {

    @Column(nullable = false)
    private String hotelPhoneNumber;

    @Column(nullable = false)
    private String hotelEmailId;

    @Column(nullable = false)
    private String hotelAddress;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

}
