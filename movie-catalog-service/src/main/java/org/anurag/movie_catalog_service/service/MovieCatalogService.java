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

    @HystrixCommand(
            fallbackMethod = "getFallbackCatalog",
            /* pool key refers to thread pool, same pool key means same thread pool */
            threadPoolKey = "movieInfoPool",
            threadPoolProperties = {
                    /* core size = total threads for this pool */
                    @HystrixProperty(name = "coreSize", value = "20"),
                    /* queue size = max requests which will be in waiting if thread pool is full */
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
            }
    )
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
