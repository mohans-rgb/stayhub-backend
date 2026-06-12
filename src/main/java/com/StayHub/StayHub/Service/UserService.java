package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.Dto.UserLoginRequest;
import com.StayHub.StayHub.Dto.UserLoginResponse;
import com.StayHub.StayHub.Dto.UserResponseDto;
import com.StayHub.StayHub.Dto.UserSignUpRequest;

public interface UserService {


    UserResponseDto signUp(UserSignUpRequest userSignUpRequest);

    UserLoginResponse login(UserLoginRequest userLoginRequest);
}
