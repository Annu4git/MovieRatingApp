package org.anurag.movie_info_service.controller;

import org.anurag.movie_info_service.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable int movieId) throws InterruptedException {
        Thread.sleep(3000);
        return new Movie(movieId, "Titanic", "A movie starring Leonardo DiCaprio and Kate Winslet");
    }
}
