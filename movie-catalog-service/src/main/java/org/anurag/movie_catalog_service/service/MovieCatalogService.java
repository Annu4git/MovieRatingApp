package org.anurag.movie_catalog_service.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.anurag.movie_catalog_service.models.CatalogItem;
import org.anurag.movie_catalog_service.models.Movie;
import org.anurag.movie_catalog_service.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieCatalogService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalog",
    commandProperties = {
            /* threshold time */
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            /* last 5 request to consider for evaluation */
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            /* % of failed request */
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            /* sleep window, how long circuit breaker will sleep before it come up */
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    })
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"
                + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
    }

    // fallback method should be simple and hard coded
    // because if fallback method fails then we need another fallback
    public CatalogItem getFallbackCatalog(Rating rating) {
        System.out.println("getFallbackCatalog executed for movie : " + rating.getMovieId());
        return new CatalogItem("Movie title not found", "", rating.getRating());
    }
}
