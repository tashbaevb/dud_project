package com.example.DUD_Project.mappers;

import com.example.DUD_Project.dto.user.UserDto;
import com.example.DUD_Project.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper mapper;

    public User convertToEntity(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    public UserDto convertToDto(User user) {
        return mapper.map(user, UserDto.class);
    }
}
