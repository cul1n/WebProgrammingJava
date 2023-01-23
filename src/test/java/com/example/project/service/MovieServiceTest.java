package com.example.project.service;

import com.example.project.exception.MovieNotFoundException;
import com.example.project.exception.UserNotFoundException;
import com.example.project.model.*;
import com.example.project.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ActorService actorService;

    @Test
    void getAllMovies() {
        Studio studio = new Studio("test name", "test location");
        List<Actor> actors = new ArrayList<>();
        Movie movie = new Movie("Movie Test", "Director test", 2000, 100, 200, 50, actors, studio);

        when(movieRepository.findAll()).thenReturn(List.of(movie));
        List<Movie> result = movieService.getAllMovies();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(movie, result.get(0));

        verify(movieRepository).findAll();
    }

    @Test
    void getMovieByIdException() {
        when(movieRepository.findById(5)).thenReturn(Optional.empty());

        MovieNotFoundException exception = assertThrows(MovieNotFoundException.class,
                () -> movieService.getMovieById(5));

        assertNotNull(exception);
        assertEquals("Movie not found.", exception.getMessage());
        verify(movieRepository).findById(5);
    }

    @Test
    void getMovieById() {
        Studio studio = new Studio("test name", "test location");
        List<Actor> actors = new ArrayList<>();
        Movie movie = new Movie("Movie Test", "Director test", 2000, 100, 200, 50, actors, studio);
        when(movieRepository.findById(0)).thenReturn(Optional.of(movie));

        Movie result = movieService.getMovieById(0);
        assertNotNull(result);
        assertEquals(movie.getMovieId(), result.getMovieId());
        assertEquals(movie.getProfit(), result.getProfit());
        assertEquals(movie.getBudget(), result.getBudget());
        assertEquals(movie.getActors(), result.getActors());
        assertEquals(movie.getLength(), result.getLength());
        assertEquals(movie.getYear(), result.getYear());
        assertEquals(movie.getDirector(), result.getDirector());
        assertEquals(movie.getStudio(), result.getStudio());
        assertEquals(movie.getName(), result.getName());

        verify(movieRepository).findById(0);
    }

    @Test
    void addActor() {
        Studio studio = new Studio("test name", "test location");
        List<Actor> actors = new ArrayList<>();
        Movie movie = new Movie("Movie Test", "Director test", 2000, 100, 200, 50, actors, studio);
        when(movieRepository.findById(0)).thenReturn(Optional.of(movie));

        Actor actor = new Actor("first_name", "last_name");
        when(actorService.getActorById(0)).thenReturn(actor);
        when(movieRepository.save(movie)).thenReturn(movie);


        Movie result = movieService.addActor(0, 0);

        assertNotNull(result);
        assertEquals(1, result.getActors().size());
        assertEquals(actor, result.getActors().get(0));

        verify(actorService).getActorById(0);
    }
}
