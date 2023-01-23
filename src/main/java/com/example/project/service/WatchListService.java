package com.example.project.service;

import com.example.project.exception.WatchListServiceException;
import com.example.project.model.User;
import com.example.project.model.WatchList;
import com.example.project.repository.WatchListRepository;
import org.springframework.stereotype.Service;

@Service
public class WatchListService {

    private WatchListRepository watchListRepository;
    private UserService userService;

    public WatchListService(WatchListRepository watchListRepository, UserService userService) {
        this.watchListRepository = watchListRepository;
        this.userService = userService;
    }

    public WatchList getWatchListOfUser(int userId) {
        User user = userService.getUserById(userId);
        return user.getWatchList();
    }

    public WatchList getWatchListById(int watchListId) {
        return watchListRepository.findById(watchListId).orElseThrow(() -> new WatchListServiceException());
    }

}
