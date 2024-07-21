package com.ladmakhi.lms.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUploader {
    String uploadImage(MultipartFile file) throws IOException;

    String uploadAndResizeImage(int height, int width, MultipartFile file) throws IOException;
}
