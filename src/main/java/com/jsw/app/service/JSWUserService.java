package com.jsw.app.service;

import com.jsw.app.dto.UserDTO;
import com.jsw.app.entity.Account;
import com.jsw.app.entity.User;
import com.jsw.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JSWUserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        Account account = new Account();
        user.setAccount(account);
        return repository.save(user);
    }

    public List<UserDTO> follow(String username) {
        String loggedInUser = getLoggedInUserUserName();
        User currentUser = repository.findByUserName(loggedInUser);
        User user = repository.findByUserName(username);
        if (user != null) {
            user.getAccount().getFollowors().add(currentUser);
            repository.save(user);
            currentUser.getAccount().getFollowings().add(user);
            repository.save(currentUser);
        }
        return getFollowings();
    }

    public List<UserDTO> getFollowers() {
        String loggedInUser = getLoggedInUserUserName();
        User currentUser = repository.findByUserName(loggedInUser);
        List<User> followers = currentUser.getAccount().getFollowors();
        return followers.stream().map(f -> new UserDTO(f.getUserName(), null, f.getEmail())).collect(Collectors.toList());
    }

    public List<UserDTO> getFollowings() {
        String loggedInUser = getLoggedInUserUserName();
        User currentUser = repository.findByUserName(loggedInUser);
        List<User> followers = currentUser.getAccount().getFollowings();
        return followers.stream().map(f -> new UserDTO(f.getUserName(), null, f.getEmail())).collect(Collectors.toList());
    }

    public static String getLoggedInUserUserName() {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null && user.getUsername() != null) {
            return user.getUsername();
        } else {
            return null;
        }
    }
}
