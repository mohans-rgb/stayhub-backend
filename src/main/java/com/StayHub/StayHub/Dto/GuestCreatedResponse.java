package com.StayHub.StayHub.Dto;

import com.StayHub.StayHub.Enums.Gender;
import lombok.Data;

@Data
public class GuestCreatedResponse {

    private Long guestId;

    private String guestName;

    private String guestEmail;

    private Integer guestAge;

    private Gender guestGender;
}
