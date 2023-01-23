package com.example.project.service;

import com.example.project.exception.UserNotFoundException;
import com.example.project.model.User;
import com.example.project.model.WatchList;
import com.example.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void getUserByIdException() {
        when(userRepository.findById(5)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.getUserById(5));

        assertNotNull(exception);
        assertEquals("User not found.", exception.getMessage());
        verify(userRepository).findById(5);
    }

    @Test
    void getUserById() {
        WatchList watchList = new WatchList();
        User user = new User("userTest", "userTest@email.com", watchList);
        when(userRepository.findById(0)).thenReturn(Optional.of(user));

        User result = userService.getUserById(0);
        assertNotNull(result);
        assertEquals(user.getUserId(), result.getUserId());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getWatchList(), result.getWatchList());

        verify(userRepository).findById(0);
    }

    @Test
    void getAllUsers() {
        WatchList watchList = new WatchList();
        User user = new User("userTest", "userTest@email.com", watchList);

        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));

        verify(userRepository).findAll();
    }
}
