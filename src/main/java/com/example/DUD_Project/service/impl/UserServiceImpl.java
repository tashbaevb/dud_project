package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.dto.user.UserResponseDto;
import com.example.DUD_Project.entity.Level;
import com.example.DUD_Project.entity.user.User;
import com.example.DUD_Project.entity.user.UserLevel;
import com.example.DUD_Project.repository.LevelRepository;
import com.example.DUD_Project.repository.UserLevelRepository;
import com.example.DUD_Project.repository.UserRepository;
import com.example.DUD_Project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LevelRepository levelRepository;
    private final UserLevelRepository userLevelRepository;

    @Override
    public UserResponseDto getUserProfile(Authentication authentication) {
        String userEmail = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserResponseDto responseDto = new UserResponseDto();
            responseDto.setEmail(user.getEmail());

            List<String> levelNames = user.getUserLevels().stream()
                    .map(usersLevel -> usersLevel.getLevel().getLevelName())
                    .collect(Collectors.toList());

            responseDto.setLevelNames(levelNames);
            return responseDto;
        } else {
            throw new NotFoundException("User not found");
        }
    }

    @Override
    public User update(User newUser, List<Integer> levelIds, Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());

        if (user.isPresent()) {
            User oldUser = user.get();
            oldUser.setEmail(newUser.getEmail());
            User updatedUser = userRepository.save(oldUser);

            // Обновление уровней пользователя
            for (Integer levelId : levelIds) {
                Level level = levelRepository.findById(levelId)
                        .orElseThrow(() -> new NotFoundException("Level not found"));

                if (oldUser.getUserLevels().stream().noneMatch(ul -> ul.getLevel().getId().equals(levelId))) {
                    // Если уровень еще не присутствует у пользователя, добавляем его
                    UserLevel userLevel = new UserLevel();
                    userLevel.setUser(updatedUser);
                    userLevel.setLevel(level);
                    userLevelRepository.save(userLevel);
                }
            }

            // Удаление уровней, которые не были выбраны при обновлении
            List<Integer> existingLevelIds = oldUser.getUserLevels().stream()
                    .map(userLevel -> userLevel.getLevel().getId())
                    .collect(Collectors.toList());

            for (Integer existingLevelId : existingLevelIds) {
                if (!levelIds.contains(existingLevelId)) {
                    // Удаление уровня, если его нет в новом списке уровней пользователя
                    UserLevel userLevelToDelete = oldUser.getUserLevels().stream()
                            .filter(userLevel -> userLevel.getLevel().getId().equals(existingLevelId))
                            .findFirst()
                            .orElseThrow(() -> new NotFoundException("User level not found"));

                    userLevelRepository.delete(userLevelToDelete);
                }
            }

            return updatedUser;
        } else {
            throw new NotFoundException("User not found");
        }
    }



    @Override
    public void delete(Authentication authentication) {
        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));

        userRepository.deleteById(currentUser.getId());
    }
}
