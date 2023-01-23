package com.example.project.model;


import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studioId;

    @NotBlank(message = "Invalid name")
    private String name;
    @NotBlank(message = "Invalid location")
    private String location;

    @OneToMany(mappedBy = "studio")
    private List<Movie> movies;

    public Studio(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Studio() {
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "studioId=" + studioId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
