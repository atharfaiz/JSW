package com.jsw.app.controller;

import com.jsw.app.exceptions.CustomException;
import com.jsw.app.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@Api("Create Post")
public class CreatePostController {


    @Autowired
    FileService fileService;


    @PostMapping("/upload/image")
    @ApiOperation("Upload image post")
    public ResponseEntity<String> uploadImagePost(@RequestBody MultipartFile file) {
        ResponseEntity<String> response = null;
        try {
            fileService.uploadFile(file);
            response = new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }
        return response;
    }

    @GetMapping("/images")
    @ApiOperation("Get all uploaded image posts")
    public ResponseEntity<List<String>> getAllImagePosts() {
        ResponseEntity<List<String>> response = null;
        try {
            response = new ResponseEntity<>(fileService.getUploadedImages(), HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }
        return response;
    }
}
