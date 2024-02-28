package com.example.DUD_Project.service.impl.security;

import com.example.DUD_Project.entity.User;
import com.example.DUD_Project.enums.UserRole;
import com.example.DUD_Project.repository.UserRepository;
import com.example.DUD_Project.service.security.AuthService;
import com.example.DUD_Project.service.security.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${resetUrl}")
    private String resetUrl;

    @Override
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.USER_ROLE);
        userRepository.save(user);
    }

    @Override
    public boolean isPresentEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String resetPassword(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return "Email not found";
        }

        String resetToken = UUID.randomUUID().toString();
        user.get().setResetToken(resetToken);
        user.get().setResetTokenExpireTime(LocalDateTime.now().plusMinutes(60));
        userRepository.save(user.get());

        String resetUrlFin = resetUrl + resetToken;
        String emailText = "Hallo! " +
                "\n\nBitte folgen Sie diesem Link, um Ihr Passwort zurückzusetzen: " + resetUrlFin;

        emailService.sendMessage(email, "Passwort zurückzusetzen", emailText);
        return "Ein Link zum Zurücksetzen des Passworts wurde an Ihre E-Mail-Adresse gesendet " + email;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String saveNewPassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken);
        if (user == null || user.getResetTokenExpireTime().isBefore(LocalDateTime.now()))
            return "Der Link zum Zurücksetzen des Passworts ist veraltet";

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpireTime(null);
        userRepository.save(user);
        return "Das Passwort wurde erfolgreich verändert. ";
    }
}
