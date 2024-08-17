package org.anurag.movie_catalog_service.controller;

import org.anurag.movie_catalog_service.models.CatalogItem;
import org.anurag.movie_catalog_service.models.Movie;
import org.anurag.movie_catalog_service.models.Rating;
import org.anurag.movie_catalog_service.models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;

    // Input :  We will get user id as input
    // 1. first we will fetch all the rated movies of this user from Ratings Data Service
    // 2. then we will fetch movie info of every movie from MovieInfoService
    // 3. Put them all together
    // Output : Return movies with details, rated by this user
    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        // 1. Hard coding step 1

        UserRatings userRatings = restTemplate.getForObject("http://localhost:8083/ratingsdata/" + userId,
                UserRatings.class);

        // 2.
        System.out.println("calling movie info service asynchronously");

        return userRatings.getRatings().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"
                    + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
        }).collect(Collectors.toList());
    }
}
