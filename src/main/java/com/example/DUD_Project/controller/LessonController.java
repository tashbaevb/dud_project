package com.example.DUD_Project.controller;

import com.example.DUD_Project.dto.GrammarResponseDto;
import com.example.DUD_Project.entity.Grammar;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.service.GrammarService;
import com.example.DUD_Project.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;
    private final GrammarService grammarService;

    @PostMapping("/create")
    public Lesson create(@RequestBody Lesson lesson) {
        return lessonService.createLesson(lesson);
    }

    @GetMapping("/get/{lessonId}/type={typeOfLesson}")
    public Object getLesson(@PathVariable Integer lessonId, @PathVariable String typeOfLesson) {
        Lesson lesson = lessonService.findById(lessonId);
        if (lesson == null) {
            return "Lesson not found";
        }

        switch (typeOfLesson) {
            case "grammar":
                return new GrammarResponseDto(lesson.getTitle(), lesson.getDescription());
//            case "listening":
//                Listening listening = lesson.getListening();
//                if (listening == null) {
//                    return "Listening lesson not found";
//                }
//                return new ListeningResponse(listening.getTitle(), listening.getDescription(), listening.getMp3Path(),
//                        listening.getQuestions1(), listening.getQuestions2());
//            case "reading":
//                Reading reading = lesson.getReading();
//                if (reading == null) {
//                    return "Reading lesson not found";
//                }
//                return new ReadingResponse(reading.getTitle(), reading.getDescription(), reading.getImgPath(),
//                        reading.getQuestions1(), reading.getQuestions2());
            default:
                return "Invalid lesson type";
        }
    }

    @GetMapping("/get/{grammarId}/grammar")
    public Object getLesson(@PathVariable Integer grammarId) {
        Grammar grammar = grammarService.getGrammarById(grammarId);
        if (grammar == null) {
            return "Lesson not found";
        }

        return new GrammarResponseDto(grammar.getTitle(), grammar.getDescription());
    }
}
