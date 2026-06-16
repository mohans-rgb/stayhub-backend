package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.Dto.UserLoginRequest;
import com.StayHub.StayHub.Dto.UserLoginResponse;
import com.StayHub.StayHub.Dto.UserResponseDto;
import com.StayHub.StayHub.Dto.UserSignUpRequest;
import com.StayHub.StayHub.Enums.Role;
import com.StayHub.StayHub.Exception.BadRequestException;
import com.StayHub.StayHub.Exception.ResourceNotFoundException;
import com.StayHub.StayHub.Repository.UserRepository;
import com.StayHub.StayHub.Security.JwtService;
import com.StayHub.StayHub.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponseDto signUp( UserSignUpRequest userSignUpRequest) {
        boolean emailExists = userRepository.existsByEmail(userSignUpRequest.getEmail());
        if(emailExists){
            throw new BadRequestException(
                    "Email already exists"
            );        }
        User signUpUser =modelMapper.map(userSignUpRequest,User.class);
        signUpUser.setRole(Role.CUSTOMER);
        signUpUser.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));

        User savedUser = userRepository.save(signUpUser);

        return modelMapper.map(savedUser,UserResponseDto.class);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {

        User user = userRepository
                .findByEmail(userLoginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found" +  userLoginRequest.getEmail()));

        boolean isValid =
                passwordEncoder.matches(
                        userLoginRequest.getPassword(),
                        user.getPassword());

        if(!isValid) {
            throw new BadRequestException(
                    "Invalid password"
            );        }

        String token = jwtService.generateToken(user);

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setToken(token);

        return userLoginResponse;
    }
}
