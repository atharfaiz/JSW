package com.jsw.app.controller;

import com.jsw.app.dto.ResponseDTO;
import com.jsw.app.dto.UserDTO;
import com.jsw.app.exceptions.CustomException;
import com.jsw.app.service.JSWUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api("Follow operations")
public class FollowController {


    @Autowired
    JSWUserService userService;

    @PutMapping("/follow")
    @ApiOperation("Follow user")
    public ResponseEntity<ResponseDTO<List<UserDTO>>> follow(@RequestParam String username) {
        ResponseEntity<ResponseDTO<List<UserDTO>>> response = null;
        try {
            response = new ResponseEntity<>(new ResponseDTO<>(Arrays.asList("You have successfully followed" + username),
                    null, userService.follow(username)), HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }
        return response;
    }

    @GetMapping("/followers")
    @ApiOperation("Get all followers")
    public ResponseEntity<List<UserDTO>> followers() {
        ResponseEntity<List<UserDTO>> response;
        try {
            response = new ResponseEntity<>(userService.getFollowers(), HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }
        return response;
    }

    @GetMapping("/followings")
    @ApiOperation("Get all followings")
    public ResponseEntity<List<UserDTO>> followings() {
        ResponseEntity<List<UserDTO>> response;
        try {
            response = new ResponseEntity<>(userService.getFollowings(), HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
            //change error handling
        }
        return response;
    }

    @GetMapping("/suggestions")
    @ApiOperation("People you may know")
    public ResponseEntity<List<UserDTO>> peopleYouMayKnow() {
        ResponseEntity<List<UserDTO>> response;
        try {
            response = new ResponseEntity<>(userService.showSecondLevelConnects(), HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }
        return response;
    }
}
