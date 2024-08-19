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

    @HystrixCommand(
            fallbackMethod = "getFallbackUserRating",
            threadPoolKey = "ratingsDataPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
            }
    )
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
