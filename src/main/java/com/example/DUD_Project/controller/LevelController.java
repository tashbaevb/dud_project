package com.example.DUD_Project.controller;

import com.example.DUD_Project.entity.Level;
import com.example.DUD_Project.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/level")
public class LevelController {

    private final LevelService levelService;

    @PostMapping("/create")
    public Level createLevel(@RequestBody Level level) {
        return levelService.createLevel(level);
    }
}
