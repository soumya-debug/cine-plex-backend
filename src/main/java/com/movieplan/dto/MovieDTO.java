package com.movieplan.dto;

import com.movieplan.model.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieDTO {
    private long id;
    private String name;
    private String duration;
    private String language;
    private String genre;
    private String banner;

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
}
