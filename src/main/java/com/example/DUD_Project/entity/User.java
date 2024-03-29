package com.example.DUD_Project.entity;

import com.example.DUD_Project.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String email, password;

    @Enumerated(EnumType.STRING)
    UserRole userRole;

    @Column(name = "reset_token")
    String resetToken;

    @Column(name = "reset_token_expire_time")
    LocalDateTime resetTokenExpireTime;
}
