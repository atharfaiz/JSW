package com.jsw.app.service;

import com.jsw.app.constant.JSWConstant;
import com.jsw.app.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    public void uploadFile(MultipartFile file) throws IOException {
        String username = JSWUserService.getLoggedInUserUserName();
        String filepath = System.getProperty(JSWConstant.JAVA_TEMP_DIR, "") + File.separator + username;
        File tempDirectory = new File(filepath);
        if (!tempDirectory.exists()) {
            tempDirectory.mkdir();
        }
        Path path = Paths.get(filepath + file.getOriginalFilename());
        Files.write(path, file.getBytes());
    }

    public List<String> getUploadedImages() {
        String username = JSWUserService.getLoggedInUserUserName();
        List<String> paths;
        String filepath = System.getProperty(JSWConstant.JAVA_TEMP_DIR, "") + File.separator + username;
        File tempDirectory = new File(filepath);
        if (tempDirectory.exists() && tempDirectory.isDirectory()) {
            File[] files = tempDirectory.listFiles();
            paths = Arrays.stream(files).map(file -> {
                if (file.isFile())
                    return file.getAbsolutePath();
                return null;
            }).collect(Collectors.toList());

        } else {
            throw new CustomException("No image found", null);
        }
        return paths;
    }
}
