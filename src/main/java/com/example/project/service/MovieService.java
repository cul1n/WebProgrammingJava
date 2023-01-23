package com.example.project.service;

import com.example.project.exception.MovieNotFoundException;
import com.example.project.model.Actor;
import com.example.project.model.Movie;
import com.example.project.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private ActorService actorService;
    public MovieService(MovieRepository movieRepository, ActorService actorService) {
        this.movieRepository = movieRepository;
        this.actorService = actorService;
    }
    public Movie saveNewMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    public Movie getMovieById(int movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException());
    }

    public Movie addActor(int movieId, int actorId) {
        Movie movie = getMovieById(movieId);
        Actor actor = actorService.getActorById(actorId);
        List<Actor> actors = movie.getActors();
        actors.add(actor);
        movie.setActors(actors);

        return movieRepository.save(movie);
    }
}
