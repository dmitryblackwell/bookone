package com.blackwell.service.impl;

import com.blackwell.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public void uploadFile(MultipartFile file, final String PATH) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(PATH);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

