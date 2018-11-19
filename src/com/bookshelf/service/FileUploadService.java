package com.bookshelf.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    void uploadFile(MultipartFile file, final String PATH);
}
