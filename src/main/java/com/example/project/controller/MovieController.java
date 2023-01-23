package com.example.project.controller;

import com.example.project.model.Movie;
import com.example.project.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@Validated
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Movie> newMovie(@RequestBody Movie movie){
        return ResponseEntity.ok().body(movieService.saveNewMovie(movie));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok().body(movieService.getAllMovies());
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int movieId) {
        return ResponseEntity.ok().body(movieService.getMovieById(movieId));
    }


    @PutMapping("{movieId}/newActor/{actorId}")
    public ResponseEntity<Movie> addActor(@PathVariable int movieId, @PathVariable int actorId) {
        Movie movie = movieService.addActor(movieId, actorId);
        return ResponseEntity.ok().body(movie);
    }
}
