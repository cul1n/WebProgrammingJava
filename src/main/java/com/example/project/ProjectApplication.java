package com.example.project;

import com.example.project.model.*;
import com.example.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WatchListRepository watchListRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private StudioRepository studioRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        Actor actor1 = new Actor("marius", "marian");
        Actor actor2 = new Actor("andrei", "razvan");

        Studio studio1 = new Studio("Studio 1", "America");
        Studio studio2 = new Studio("Studio 2", "Franta");

        List<Actor> actorList1 = new ArrayList<>();
        actorList1.add(actor1);
        actorList1.add(actor2);

        List<Actor> actorList2 = new ArrayList<>();
        actorList2.add(actor2);

        Movie movie1 = new Movie("movie", "testRegizor", 2010, 90, 100, 150, actorList1, studio1);
        Movie movie2 = new Movie("movie2", "test regizor 2", 2019, 110, 50, 10, actorList2, studio2);
        Movie movie3 = new Movie("movie2", "test regizor 2", 2020, 70, 70, 250, actorList1, studio1);

        Entry entry1 = new Entry(movie1, 10);
        Entry entry2 = new Entry(movie2, 4);

        List<Entry> entries = new ArrayList<>();
        entries.add(entry1);
        entries.add(entry2);

        WatchList watchList1 = new WatchList(entries, 5);

        User user1 = new User("calin", "calin@test.gmail", watchList1);

        actorRepository.save(actor1);
        actorRepository.save(actor2);
        studioRepository.save(studio1);
        studioRepository.save(studio2);
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
        entryRepository.save(entry1);
        entryRepository.save(entry2);
        watchListRepository.save(watchList1);
        userRepository.save(user1);
    }
}
