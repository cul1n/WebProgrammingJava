package com.example.project.model;


import jakarta.persistence.*;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Entity
@Table()
public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int watchListId;
    @ManyToMany
    private List<Entry> entries;
    @Min(1)
    private double averageGrade;

    public WatchList(List<Entry> entries, int averageGrade) {
        this.entries = entries;
        this.averageGrade = averageGrade;
        this.computeGrade();
    }

    public WatchList() {
    }

    public int getWatchListId() {
        return watchListId;
    }

    public void setWatchListId(int watchListId) {
        this.watchListId = watchListId;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
        this.computeGrade();
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void computeGrade() {
        double grade = 0;
        for(Entry entry : this.entries) {
            grade += entry.getGrade();
        }
        if (this.entries.size() == 0) {
            this.averageGrade = 1;
            return;
        }
        BigDecimal bd = BigDecimal.valueOf(grade / this.entries.size());
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        this.averageGrade = bd.doubleValue();
    }


    @Override
    public String toString() {
        return "WatchList{" +
                "watchListId=" + watchListId +
                ", entries=" + entries +
                ", averageGrade=" + averageGrade +
                '}';
    }
}
