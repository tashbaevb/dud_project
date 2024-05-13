package com.example.DUD_Project.mappers;

import com.example.DUD_Project.dto.user.RegistrationRequest;
import com.example.DUD_Project.dto.user.UpdateRequest;
import com.example.DUD_Project.dto.user.UserDto;
import com.example.DUD_Project.dto.user.UserResponseDto;
import com.example.DUD_Project.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper mapper;

    public User convertToEntity(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    public User convertToEntity(UpdateRequest updateRequest) {
        return mapper.map(updateRequest, User.class);
    }


    public User convertToEntity(RegistrationRequest registrationRequest) {
        return mapper.map(registrationRequest, User.class);
    }

    public UserDto convertToDto(User user) {
        return mapper.map(user, UserDto.class);
    }
}
