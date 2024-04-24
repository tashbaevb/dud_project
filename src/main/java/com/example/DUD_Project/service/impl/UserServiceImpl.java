package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.dto.user.UserResponseDto;
import com.example.DUD_Project.entity.user.User;
import com.example.DUD_Project.repository.UserRepository;
import com.example.DUD_Project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUserProfile(Authentication authentication) {
        String userEmail = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserResponseDto responseDto = new UserResponseDto();
            responseDto.setEmail(user.getEmail());

            List<String> levelNames = user.getUsersLevels().stream()
                    .map(usersLevel -> usersLevel.getLevel().getLevelName())
                    .collect(Collectors.toList());

            responseDto.setLevelNames(levelNames);
            return responseDto;
        } else {
            throw new NotFoundException("User not found");
        }
    }

    @Override
    public User update(User newUser, Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());

        if (user.isPresent()) {
            User oldUser = user.get();
            oldUser.setEmail(newUser.getEmail());
            return userRepository.save(oldUser);
        } else throw new NotFoundException("User not found");
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Authentication authentication) {
        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));

        userRepository.deleteById(currentUser.getId());
    }
}
