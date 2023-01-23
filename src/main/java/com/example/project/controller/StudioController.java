package com.example.project.controller;

import com.example.project.model.Movie;
import com.example.project.model.Studio;
import com.example.project.service.StudioService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/studio")
@Validated
public class StudioController {

    private final StudioService studioService;

    public StudioController(StudioService studioService) {
        this.studioService = studioService;
    }

    @GetMapping
    public ResponseEntity<List<Studio>> getAllStudios() {
        return ResponseEntity.ok().body(studioService.getAllStudios());
    }

    @GetMapping("/{studioId}")
    public ResponseEntity<List<Movie>> getMoviesOfStudioSorted(@PathVariable int studioId) {
        return ResponseEntity.ok().body(studioService.getMoviesOfStudioSorted(studioId));
    }
}
