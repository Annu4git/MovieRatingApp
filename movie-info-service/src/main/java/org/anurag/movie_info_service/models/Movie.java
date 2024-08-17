package org.anurag.movie_info_service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private int movieId;

    private String movieName;

    private String movieDescription;
}
