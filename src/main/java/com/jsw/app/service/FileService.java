package com.jsw.app.service;

import com.jsw.app.constant.JSWConstant;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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
        Path resolvePath = Paths.get(filepath).toAbsolutePath().normalize().resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), resolvePath, StandardCopyOption.REPLACE_EXISTING);
    }

    public List<String> getUploadedImages() {
        String username = JSWUserService.getLoggedInUserUserName();
        List<String> paths = new ArrayList<>();
        String filepath = System.getProperty(JSWConstant.JAVA_TEMP_DIR, "") + File.separator + username;
        File tempDirectory = new File(filepath);
        if (tempDirectory.exists() && tempDirectory.isDirectory()) {
            File[] files = tempDirectory.listFiles();
            paths = Arrays.stream(files).map(file -> {
                if (file.isFile())
                    return file.getAbsolutePath();
                return null;
            }).collect(Collectors.toList());

        }
        return paths;
    }
}
