package com.jsw.app;

import com.jsw.app.entity.User;
import com.jsw.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JSWSocialMediaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JSWSocialMediaApplication.class, args);
    }

}
