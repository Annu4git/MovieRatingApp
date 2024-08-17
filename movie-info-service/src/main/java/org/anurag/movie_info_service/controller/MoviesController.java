package org.anurag.movie_info_service.controller;

import org.anurag.movie_info_service.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    Map<Integer, Movie> movieMap = new HashMap<>();

    MoviesController() {
        movieMap.put(501, new Movie(501, "Titanic", "A movie starring Leonardo DiCaprio and Kate Winslet"));
        movieMap.put(502, new Movie(502, "DDLJ", "Dilwale Dulhania Le Jayenge"));
        movieMap.put(504, new Movie(504, "RDB", "Rang De Basanti"));
        movieMap.put(531, new Movie(531, "3 Idiots", "A favourite movie of all time"));
    }

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable int movieId) throws InterruptedException {
        return movieMap.get(movieId);
    }
}
