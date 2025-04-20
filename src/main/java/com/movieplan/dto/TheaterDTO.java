package com.movieplan.dto;

public class TheaterDTO {

    private long id;
    private String theatreName;
    private String theatreAddress;
    private long movieId;

    public TheaterDTO() {}

    public TheaterDTO(long id, String theatreName, String theatreAddress, long movieId) {
        this.id = id;
        this.theatreName = theatreName;
        this.theatreAddress = theatreAddress;
        this.movieId = movieId;
    }

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getTheatreName() { return theatreName; }
    public void setTheatreName(String theatreName) { this.theatreName = theatreName; }
    public String getTheatreAddress() { return theatreAddress; }
    public void setTheatreAddress(String theatreAddress) { this.theatreAddress = theatreAddress; }
    public long getMovieId() { return movieId; }
    public void setMovieId(long movieId) { this.movieId = movieId; }
}
