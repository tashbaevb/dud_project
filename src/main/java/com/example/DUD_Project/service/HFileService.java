package com.example.DUD_Project.service;

import com.example.DUD_Project.entity.HFile;
import org.springframework.web.multipart.MultipartFile;

public interface HFileService {

    HFile save(String name, MultipartFile file);

    HFile findById(Integer id);

}
