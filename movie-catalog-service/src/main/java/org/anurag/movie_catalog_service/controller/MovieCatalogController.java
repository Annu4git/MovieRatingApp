package org.anurag.movie_catalog_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.anurag.movie_catalog_service.models.CatalogItem;
import org.anurag.movie_catalog_service.models.Movie;
import org.anurag.movie_catalog_service.models.Rating;
import org.anurag.movie_catalog_service.models.UserRatings;
import org.anurag.movie_catalog_service.service.MovieCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    MovieCatalogService movieCatalogService;

    @Autowired
    private RestTemplate restTemplate;

    private int attempt=1;

    @Value("${management.health.circuitbreakers.enabled}")
    private boolean enabled;

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "catalogService",fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        System.out.println("retry method called "+attempt++ +" times.");
        System.out.println("********* : " + enabled);

        UserRatings userRatings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/" + userId,
                UserRatings.class);

        /*UserRatings userRatings = restClient.get()
                .uri("http://ratings-data-service/ratingsdata/" + userId)
                .retrieve()
                .body(UserRatings.class);*/

        return userRatings.getRatings().stream().map(rating -> {
//            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"
                    + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
        }).collect(Collectors.toList());
    }


    public List<CatalogItem> getFallbackCatalog(Exception e) {
        System.out.println("Fallback executed!");
        return Arrays.asList(
                new CatalogItem("No movie", "", 0)
        );
    }


}
