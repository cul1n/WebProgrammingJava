package com.example.project.model;

import jakarta.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table()
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entryId;
    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @NotNull
    @Min(value = 1, message = "The grade must be higher than 1")
    @Max(value = 10, message = "The grade must be lower than 10")
    private int grade;

    public Entry(Movie movie, int grade) {
        this.movie = movie;
        this.grade = grade;
    }

    public Entry() {
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "entryId=" + entryId +
                ", movie=" + movie +
                ", grade=" + grade +
                '}';
    }
}
