package com.ladmakhi.lms.implmentations;

import com.ladmakhi.lms.common.util.FilePathUtil;
import com.ladmakhi.lms.services.VideoUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class VideoUploaderImpl implements VideoUploader {
    private final FilePathUtil filePathUtil;

    @Override
    public String uploadVideo(MultipartFile file) throws IOException {
        Path filePathRoot = filePathUtil.prepareRootPath("/videos");
        String fileOutput = filePathUtil.prepareOutputFilePath(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePathRoot.resolve(fileOutput), StandardCopyOption.REPLACE_EXISTING);
        return fileOutput;
    }

    @Override
    public String uploadVideoAndThumbnail(MultipartFile file) throws IOException {
        //  TODO get screenshot and upload video
        return null;
    }
}
