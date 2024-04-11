package com.example.DUD_Project.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Integer id;
    String email, password;

    Set<Integer> levelIds;
}
