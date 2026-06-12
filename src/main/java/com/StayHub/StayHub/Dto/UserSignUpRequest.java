package com.StayHub.StayHub.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequest {
    private String name;

    private String password;

    private String email;
}
