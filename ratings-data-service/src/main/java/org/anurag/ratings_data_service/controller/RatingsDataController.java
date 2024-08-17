package org.anurag.ratings_data_service.controller;

import org.anurag.ratings_data_service.models.Rating;
import org.anurag.ratings_data_service.models.UserRatings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataController {

    private Map<Integer, List<Rating>> userMovieRatings = new HashMap<>();

    RatingsDataController() {
        userMovieRatings.put(101, Arrays.asList(
                new Rating(501, 8),
                new Rating(504, 9),
                new Rating(531, 3)));
        userMovieRatings.put(102, Arrays.asList(
                new Rating(531, 2),
                new Rating(501, 4),
                new Rating(502, 6)));
    }

    @GetMapping("/{userId}")
    public UserRatings getRating(@PathVariable int userId) {

        List<Rating> ratings =  userMovieRatings.get(userId);
        return new UserRatings(userId, ratings);
    }
}
