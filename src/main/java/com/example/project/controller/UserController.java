package com.example.project.controller;


import com.example.project.model.User;
import com.example.project.model.WatchList;
import com.example.project.service.UserService;
import com.example.project.service.WatchListService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private final UserService userService;
    private final WatchListService watchListService;

    public UserController(UserService userService, WatchListService watchListService) {
        this.userService = userService;
        this.watchListService = watchListService;
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    @GetMapping("/{userId}/watchlist")
    public ResponseEntity<WatchList> getWatchListOfUser(@PathVariable int userId) {
        return ResponseEntity.ok().body(watchListService.getWatchListOfUser(userId));
    }
}
