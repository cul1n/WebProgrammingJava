package com.example.project.service;

import com.example.project.exception.EntryNotFoundException;
import com.example.project.exception.UserNotFoundException;
import com.example.project.model.*;
import com.example.project.repository.EntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntryServiceTests {

    @InjectMocks
    private EntryService entryService;

    @Mock
    private EntryRepository entryRepository;
    @Mock
    private MovieService movieService;
    @Mock
    private WatchListService watchListService;

    @Test
    void saveNewEntry(){
        List<Entry> entries = new ArrayList<>();
        WatchList watchList = new WatchList(entries, 1);
        List<Actor> actors = new ArrayList<>();
        Studio studio = new Studio("Test", "testLocation");
        Movie movie = new Movie("Movie Test", "Director test", 2000, 100, 200, 50, actors, studio);

        when(watchListService.getWatchListById(0)).thenReturn(watchList);
        when(movieService.getMovieById(0)).thenReturn(movie);

        Entry entry = new Entry(movie, 1);

        entries = watchList.getEntries();
        entries.add(entry);
        watchList.setEntries(entries);

        when(entryRepository.save(any(Entry.class))).thenReturn(entry);

        Entry result = entryService.saveNewEntry(watchList.getWatchListId(), movie.getMovieId(), entry);

        assertNotNull(result);
        assertEquals(watchList.getEntries().get(0), result);
        assertEquals(movie, result.getMovie());

        verify(watchListService).getWatchListById(0);
        verify(movieService).getMovieById(0);

    }

    @Test
    void getEntryByIdException() {
        when(entryRepository.findById(5)).thenReturn(Optional.empty());

        EntryNotFoundException exception = assertThrows(EntryNotFoundException.class,
                () -> entryService.getEntryById(5));

        Assertions.assertNotNull(exception);
        assertEquals("Entry not found.", exception.getMessage());
        verify(entryRepository).findById(5);
    }

    @Test
    void getUserById() {
        List<Entry> entries = new ArrayList<>();
        WatchList watchList = new WatchList(entries, 1);
        List<Actor> actors = new ArrayList<>();
        Studio studio = new Studio("Test", "testLocation");
        Movie movie = new Movie("Movie Test", "Director test", 2000, 100, 200, 50, actors, studio);

        Entry entry = new Entry(movie, 1);

        entries = watchList.getEntries();
        entries.add(entry);
        watchList.setEntries(entries);

        when(entryRepository.findById(0)).thenReturn(Optional.of(entry));

        Entry result = entryService.getEntryById(0);
        Assertions.assertNotNull(result);
        assertEquals(entry.getEntryId(), result.getEntryId());
        assertEquals(entry.getGrade(), result.getGrade());
        assertEquals(entry.getMovie(), result.getMovie());

        verify(entryRepository).findById(0);
    }



}
