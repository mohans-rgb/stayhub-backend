package com.StayHub.StayHub.Dto;

import com.StayHub.StayHub.Enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;

public class UserResponseDto {

    private Long id;

    private String name;

    private String email;

    private Role role ;


}
