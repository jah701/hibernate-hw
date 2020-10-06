package com.dev.cinema.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie_sessions")
public class MovieSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private CinemaHall cinemaHall;
    private LocalDate showtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public LocalDate getShowTime() {
        return showtime;
    }

    public void setShowTime(LocalDate showTime) {
        this.showtime = showTime;
    }

    @Override
    public String toString() {
        return "MovieSession{" + "id=" + id + ", movie='" + movie + '\''
                + ", cinemaHall=" + cinemaHall + ", showTime=" + showtime + '}';
    }
}