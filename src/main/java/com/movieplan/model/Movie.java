package com.movieplan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String duration;
    @Column
    private String language;
    @Column
    private String genre;
    @Column
    private String banner;
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Theater> theater;
}
