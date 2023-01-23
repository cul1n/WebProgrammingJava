package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table()
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    @NotBlank(message = "Invalid movie name")
    private String name;
    @NotBlank(message = "Invalid director")
    private String director;
    @NotNull
    @Min(value = 1920, message = "Enter a valid year")
    private int year;
    @NotNull
    private int length;
    @NotNull
    private int budget;
    @NotNull
    private int profit;
    @ManyToMany
    private List<Actor> actors;
    @ManyToOne
    @JoinColumn(name = "studioId")
    @JsonIgnore
    private Studio studio;

    public Movie(String name, String director, int year, int length, int budget, int profit, List<Actor> actors, Studio studio) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.length = length;
        this.budget = budget;
        this.profit = profit;
        this.actors = actors;
        this.studio = studio;
    }

    public Movie() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", length=" + length +
                ", budget=" + budget +
                ", profit=" + profit +
                ", actors=" + actors +
                ", studio=" + studio +
                '}';
    }
}
