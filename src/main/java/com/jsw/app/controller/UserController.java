package com.jsw.app.controller;

import com.jsw.app.dto.AuthRequest;
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
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        ResponseEntity<UserDTO> response = null;
        try {
            User user = userDetailsService.createUser(dto);
            response = new ResponseEntity<>(new UserDTO(user.getUserName(), null, user.getEmail()), HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }
        return response;
    }
    @PostMapping("/signin")
    @ApiOperation("User login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        ResponseEntity<String> response = null;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );
            response = new ResponseEntity<>(jwtUtil.generateToken(request.getUserName()), HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }
        return response;
    }
}
