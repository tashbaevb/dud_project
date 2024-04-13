package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.entity.HFile;
import com.example.DUD_Project.repository.HFileRepository;
import com.example.DUD_Project.service.HFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class HFileServiceImpl implements HFileService {

    private final HFileRepository hFileRepository;

    @Override
    public HFile save(String name, MultipartFile file) {
        try {
            HFile hFile = new HFile();
            hFile.setName(name);
            hFile.setFileData(file.getBytes());

            return hFileRepository.save(hFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store the file", e);
        }
    }

    @Override
    public HFile findById(Integer id) {
        return hFileRepository.findById(id).orElse(null);
    }
}
