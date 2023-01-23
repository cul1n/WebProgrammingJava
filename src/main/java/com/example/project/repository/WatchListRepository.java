package com.example.project.repository;

import com.example.project.model.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepository extends JpaRepository<WatchList, Integer> {
}
