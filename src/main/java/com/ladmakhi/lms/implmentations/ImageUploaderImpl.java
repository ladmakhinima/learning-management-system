package com.ladmakhi.lms.implmentations;

import com.ladmakhi.lms.common.util.FilePathUtil;
import com.ladmakhi.lms.services.ImageUploader;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class ImageUploaderImpl implements ImageUploader {
    private final FilePathUtil filePathUtil;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        Path baseDir = filePathUtil.prepareRootPath("/images");
        InputStream inputStream = file.getInputStream();
        String outputFilename = filePathUtil.prepareOutputFilePath(file.getOriginalFilename());
        Files.copy(inputStream, baseDir.resolve(outputFilename));
        return outputFilename;
    }

    @Override
    public String uploadAndResizeImage(int width, int height, MultipartFile file) throws IOException {
        Path baseDir = filePathUtil.prepareRootPath("/images");
        String outputFilename = filePathUtil.prepareOutputFilePath(file.getOriginalFilename());
        File outputFile = baseDir.resolve(outputFilename).toFile();

        Thumbnails.of(file.getInputStream())
                .size(width, height)
                .toFile(outputFile);
        return outputFilename;
    }
}
