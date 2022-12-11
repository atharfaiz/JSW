package com.jsw.app.controller;

import com.jsw.app.dto.AuthRequest;
import com.jsw.app.dto.ResponseDTO;
import com.jsw.app.dto.UserDTO;
import com.jsw.app.entity.User;
import com.jsw.app.exceptions.CustomException;
import com.jsw.app.service.JSWUserService;
import com.jsw.app.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Api("User Api")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JSWUserService userDetailsService;

    @PostMapping("/signup")
    @ApiOperation("Create user")
    public ResponseEntity<ResponseDTO<UserDTO>> createUser(@RequestBody UserDTO dto) {
        ResponseEntity<ResponseDTO<UserDTO>> response = null;
        try {
            User user = userDetailsService.createUser(dto);
            ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>();
            responseDTO.getSuccessMessages().add("User created successfully");
            responseDTO.setO(new UserDTO(user.getUserName(), null, user.getEmail()));
            response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }
        return response;
    }

    @PostMapping("/signin")
    @ApiOperation("User login")
    public ResponseEntity<ResponseDTO<String>> login(@RequestBody AuthRequest request) {
        ResponseEntity<ResponseDTO<String>> response = null;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );

            response = new ResponseEntity<>(new ResponseDTO<>(Arrays.asList("User authenticated successfully"),
                    null, jwtUtil.generateToken(request.getUserName())), HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }
        return response;
    }
}
