package com.ladmakhi.lms.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Random;

@Component
public class FilePathUtil {
    @Value("${upload_course_dir}")
    private String UPLOAD_DIR;

    public String prepareOutputFilePath(String originalName) {
        return String.format(
                "%s-%s%s",
                new Date().getTime(),
                new Random().nextLong(10000000000L),
                originalName.substring(originalName.lastIndexOf("."))
        );
    }

    public Path prepareRootPath(String subDir) throws IOException {
        Path baseDir = Paths.get(UPLOAD_DIR + subDir);
        if (!Files.exists(baseDir)) {
            Files.createDirectories(baseDir);
        }
        return baseDir;
    }
}
