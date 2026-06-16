package com.StayHub.StayHub.Controllers;

import com.StayHub.StayHub.Dto.UserLoginRequest;
import com.StayHub.StayHub.Dto.UserLoginResponse;
import com.StayHub.StayHub.Dto.UserResponseDto;
import com.StayHub.StayHub.Dto.UserSignUpRequest;
import com.StayHub.StayHub.Service.UserService;
import com.StayHub.StayHub.Service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class  AuthControllers {

    private final UserService userService;

    @PostMapping("/signup")
    public UserResponseDto userSignUp(@RequestBody  @Valid  UserSignUpRequest userSignUpRequest){
        return userService.signUp(userSignUpRequest);
    }
    @PostMapping("/login")
    public UserLoginResponse userLogin(@RequestBody @Valid  UserLoginRequest userLoginRequest){

        System.out.println("LOGIN ENDPOINT HIT");

        return userService.login(userLoginRequest);
    }

}
