package com.movieplan.model;

import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookSeatsRequest {
    private String time;
    private Date date;
    private List<String> seats;
}
