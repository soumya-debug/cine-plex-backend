package com.movieplan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TheaterDTO {
    private long id;
    private String theatreName;
    private String theatreAddress;
    private long movieId;
}
