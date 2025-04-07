// This is the model class representing the Movie entity.
package com.movieplan.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//This annotation marks this class as an entity, which will be mapped to a database table.
@Entity
public class Movie {

    // The primary key for the Movie entity, generated automatically
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generates values automatically with an identity column strategy
    @Column
    private long id;

    // The name of the movie
    @Column
    private String name;

    // The duration of the movie
    @Column
    private String duration;

    // The language in which the movie is available
    @Column
    private String language;

    // The genre of the movie
    @Column
    private String genre;

    // The URL or path to the movie's banner or poster image
    @Column
    private String banner;

    // A one-to-many relationship with theaters, mapped by the "movie" property in Theater entity
    @OneToMany(mappedBy = "movie") // Specifies the mappedBy property for the inverse side of the relationship
    private List<Theater> theater;

    // Getter method for the banner
    public String getBanner() {
        return banner;
    }

    // Setter method for the banner
    public void setBanner(String banner) {
        this.banner = banner;
    }

    // Default constructor for the Movie class
    public Movie() {
        super();
    }

    // Getter method for the ID
    public long getId() {
        return id;
    }

    // Setter method for the ID
    public void setId(long id) {
        this.id = id;
    }

    // Getter method for the name
    public String getName() {
        return name;
    }

    // Setter method for the name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for the duration
    public String getDuration() {
        return duration;
    }

    // Setter method for the duration
    public void setDuration(String duration) {
        this.duration = duration;
    }

    // Getter method for the language
    public String getLanguage() {
        return language;
    }

    // Setter method for the language
    public void setLanguage(String language) {
        this.language = language;
    }

    // Getter method for the genre
    public String getGenre() {
        return genre;
    }

    // Setter method for the genre
    public void setGenre(String genre) {
        this.genre = genre;
    }

}
