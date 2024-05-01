package com.example.DUD_Project.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {

    String email, password;

    List<Integer> levelIds;
}
