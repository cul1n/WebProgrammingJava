package com.example.project.service;

import com.example.project.exception.StudioNotFoundException;
import com.example.project.model.Movie;
import com.example.project.model.Studio;
import com.example.project.repository.StudioRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudioService {

    private StudioRepository studioRepository;
    private MovieService movieService;

    public StudioService(StudioRepository studioRepository, MovieService movieService) {
        this.studioRepository = studioRepository;
        this.movieService = movieService;
    }

    public List<Studio> getAllStudios() {
        return studioRepository.findAll();
    }

    public List<Movie> getMoviesOfStudioSorted(int studioId) {
        List<Movie> movies = movieService.getAllMovies();
        Studio studio = studioRepository.findById(studioId).orElseThrow(() -> new StudioNotFoundException());

        return movies.stream().filter(movie -> movie.getStudio() == studio)
                .sorted(Comparator.comparingInt(Movie::getProfit).reversed())
                .collect(Collectors.toList());
    }
}
