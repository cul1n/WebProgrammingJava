package com.example.project.controller;

import com.example.project.model.Entry;
import com.example.project.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entry/{watchListId}")
@Validated
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping("/{movieId}")
    public ResponseEntity<Entry> newEntry(@PathVariable int watchListId, @PathVariable int movieId, @RequestBody Entry entry) {
        return ResponseEntity.ok().body(entryService.saveNewEntry(watchListId, movieId, entry));
    }

    @DeleteMapping("/{entryId}")
    public ResponseEntity<Entry> deleteEntry(@PathVariable int watchListId, @PathVariable int entryId) {
        return ResponseEntity.ok().body(entryService.deleteEntryById(watchListId, entryId));
    }

}
