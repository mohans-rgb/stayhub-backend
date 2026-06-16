package com.StayHub.StayHub.Dto;

import com.StayHub.StayHub.Enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestCreateRequest {
    private String guestName;

    private String guestEmail;

    private Integer guestAge;

    private Gender guestGender;

}
