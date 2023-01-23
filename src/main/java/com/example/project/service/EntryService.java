package com.example.project.service;

import com.example.project.exception.EntryNotFoundException;
import com.example.project.model.Entry;
import com.example.project.model.Movie;
import com.example.project.model.WatchList;
import com.example.project.repository.EntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {
    private EntryRepository entryRepository;
    private MovieService movieService;
    private WatchListService watchListService;

    public EntryService(EntryRepository entryRepository, MovieService movieService, WatchListService watchListService) {
        this.entryRepository = entryRepository;
        this.movieService = movieService;
        this.watchListService = watchListService;
    }

    public Entry saveNewEntry(int watchListId, int movieId, Entry entry) {
        WatchList watchList = watchListService.getWatchListById(watchListId);
        Movie movie = movieService.getMovieById(movieId);
        entry.setMovie(movie);

        List<Entry> entries = watchList.getEntries();
        entries.add(entry);
        watchList.setEntries(entries);

        return entryRepository.save(entry);
    }

    public Entry deleteEntryById(int watchListId, int entryId) {
        Entry entry = getEntryById(entryId);

        WatchList watchList = watchListService.getWatchListById(watchListId);
        List<Entry> entries = watchList.getEntries();
        entries.remove(entry);
        watchList.setEntries(entries);

        entryRepository.delete(entry);
        return entry;
    }

    public Entry getEntryById(int entryId) {
        return entryRepository.findById(entryId).orElseThrow(() -> new EntryNotFoundException());
    }
}
