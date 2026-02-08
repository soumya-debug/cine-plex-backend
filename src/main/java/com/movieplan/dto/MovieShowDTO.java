package com.movieplan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieShowDTO {
    private long id;
    private String status;

    public MovieShowDTO(long id, String status) {
        this.id = id;
        this.status = status;
    }
}
