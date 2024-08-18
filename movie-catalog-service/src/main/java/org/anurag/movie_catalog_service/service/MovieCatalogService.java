package org.anurag.movie_catalog_service.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.anurag.movie_catalog_service.models.CatalogItem;
import org.anurag.movie_catalog_service.models.Movie;
import org.anurag.movie_catalog_service.models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieCatalogService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalog", commandKey = "offerCommandKey")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        UserRatings userRatings = restTemplate.getForObject("http://localhost:8083/ratingsdata/" + userId,
                UserRatings.class);

        /*UserRatings userRatings = restClient.get()
                .uri("http://ratings-data-service/ratingsdata/" + userId)
                .retrieve()
                .body(UserRatings.class);*/

        return userRatings.getRatings().stream().map(rating -> {
             Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"
//            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"
                    + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
        }).collect(Collectors.toList());
    }

    // fallback method should be simple and hard coded
    // because if fallback method fails then we need another fallback
    private List<CatalogItem> getFallbackCatalog(String userId) {
        return Arrays.asList(
                new CatalogItem("No movie", "", 0)
        );
    }
}
