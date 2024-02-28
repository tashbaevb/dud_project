package com.example.DUD_Project.service.security;

public interface EmailService {

    void sendMessage(String to, String subject, String text);
}
