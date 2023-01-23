package com.example.project.service;

import com.example.project.exception.ActorNotFoundException;
import com.example.project.model.Actor;
import com.example.project.repository.ActorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActorServiceTests {

    @InjectMocks
    private ActorService actorService;

    @Mock
    private ActorRepository actorRepository;

    @Test
    void getUserByIdException() {
        when(actorRepository.findById(5)).thenReturn(Optional.empty());

        ActorNotFoundException exception = assertThrows(ActorNotFoundException.class,
                () -> actorService.getActorById(5));

        assertNotNull(exception);
        assertEquals("Actor not found.", exception.getMessage());
        verify(actorRepository).findById(5);
    }

    @Test
    void getUserById() {
        Actor actor = new Actor("ActorTest1", "ActorTest2");
        when(actorRepository.findById(0)).thenReturn(Optional.of(actor));

        Actor result = actorService.getActorById(0);
        assertNotNull(result);
        assertEquals(actor.getActorId(), result.getActorId());
        assertEquals(actor.getFirstName(), result.getFirstName());
        assertEquals(actor.getLastName(), result.getLastName());

        verify(actorRepository).findById(0);
    }

    @Test
    void getAllActors() {
        Actor actor = new Actor("ActorTest1", "ActorTest2");

        when(actorRepository.findAll()).thenReturn(List.of(actor));
        List<Actor> result = actorService.getAllActors();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(actor, result.get(0));

        verify(actorRepository).findAll();
    }

}
