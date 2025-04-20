package com.movieplan.dto;

import com.movieplan.model.Movie;

public class MovieDTO {

    private long id;
    private String name;
    private String duration;
    private String language;
    private String genre;
    private String banner;

    public MovieDTO() {}

    public MovieDTO(long id, String name, String duration, String language, String genre, String banner) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.language = language;
        this.genre = genre;
        this.banner = banner;
    }

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.duration = movie.getDuration();
        this.language = movie.getLanguage();
        this.genre = movie.getGenre();
        this.banner = movie.getBanner();
    }

    public static MovieDTO fromEntity(Movie movie) {
        return new MovieDTO(movie);
    }

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getBanner() { return banner; }
    public void setBanner(String banner) { this.banner = banner; }
}
