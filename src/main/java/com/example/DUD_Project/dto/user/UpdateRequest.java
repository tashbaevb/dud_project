package com.example.DUD_Project.dto.user;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateRequest {

    String email;

    List<Integer> levelIds;
}
