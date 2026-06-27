package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.DTO.ProfileUpdateRequestDto;
import com.StayHub.StayHub.DTO.UserDto;
import com.StayHub.StayHub.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {



    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();

    UserDetails loadUserByUsername(String username);
}
