package com.ladmakhi.lms.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoUploader {
    String uploadVideo(MultipartFile file) throws IOException;

    String uploadVideoAndThumbnail(MultipartFile file) throws IOException;
}
