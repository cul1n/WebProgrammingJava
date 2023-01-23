package com.example.project.service;

import com.example.project.model.User;
import com.example.project.model.WatchList;
import com.example.project.repository.UserRepository;
import com.example.project.repository.WatchListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WatchListServiceTest {

    @InjectMocks
    private WatchListService watchListService;
    @Mock
    private WatchListRepository watchListRepository;
    @Mock
    private UserService userService;

    @Test
    void getWatchListById() {
        WatchList watchList = new WatchList();
        User user = new User("userTest", "userTest@email.com", watchList);
        when(watchListRepository.findById(0)).thenReturn(Optional.of(watchList));

        WatchList result = watchListService.getWatchListById(0);
        assertNotNull(result);

        verify(watchListRepository).findById(0);
    }
}
