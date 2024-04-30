package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.entity.Level;
import com.example.DUD_Project.repository.LevelRepository;
import com.example.DUD_Project.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    @Override
    public Level createLevel(Level level) {
        return levelRepository.save(level);
    }
}
