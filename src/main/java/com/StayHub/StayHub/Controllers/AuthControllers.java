package com.StayHub.StayHub.Controllers;

import com.StayHub.StayHub.Dto.UserLoginRequest;
import com.StayHub.StayHub.Dto.UserLoginResponse;
import com.StayHub.StayHub.Dto.UserResponseDto;
import com.StayHub.StayHub.Dto.UserSignUpRequest;
import com.StayHub.StayHub.Service.UserService;
import com.StayHub.StayHub.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllers {

    private final UserService userService;

    @PostMapping("/signup")
    public UserResponseDto userSignUp(@RequestBody UserSignUpRequest userSignUpRequest){
        return userService.signUp(userSignUpRequest);
    }
    @GetMapping("/login")
    public UserLoginResponse userLogin(@RequestBody UserLoginRequest userLoginRequest){
        return userService.login(userLoginRequest);
    }

}
