package com.movieplan.dto;

public class MovieShowDTO {

    private long id;
    private String status;

    public MovieShowDTO() {
    }

    public MovieShowDTO(long id, String status) {
        this.id = id;
        this.status = status;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

