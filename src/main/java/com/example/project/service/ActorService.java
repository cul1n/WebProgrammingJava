package com.example.project.service;

import com.example.project.exception.ActorNotFoundException;
import com.example.project.model.Actor;
import com.example.project.model.Movie;
import com.example.project.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    private ActorRepository actorRepository;
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Actor getActorById(int actorId) {
        return actorRepository.findById(actorId).orElseThrow(() -> new ActorNotFoundException());
    }
}
