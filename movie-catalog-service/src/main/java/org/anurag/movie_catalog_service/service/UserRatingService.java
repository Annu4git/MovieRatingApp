package org.anurag.movie_catalog_service.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.anurag.movie_catalog_service.models.CatalogItem;
import org.anurag.movie_catalog_service.models.Rating;
import org.anurag.movie_catalog_service.models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
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
    public UserRatings getUserRatings(String userId) {
        UserRatings userRatings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/" + userId,
                UserRatings.class);
        return userRatings;
    }

    // fallback method should be simple and hard coded
    // because if fallback method fails then we need another fallback
    public UserRatings getFallbackUserRating(String userId) {
        System.out.println("getFallbackUserRating executed for user : " + userId);
        return new UserRatings(1, Arrays.asList(new Rating(0, 0)));
    }

}
