package org.anurag.movie_catalog_service.controller;

import org.anurag.movie_catalog_service.models.*;
import org.anurag.movie_catalog_service.service.MovieCatalogService;
import org.anurag.movie_catalog_service.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    MovieCatalogService movieCatalogService;

    @Autowired
    UserRatingService userRatingService;

    @GetMapping("/{userId}")
    public ResponseEntity getCatalog(@PathVariable String userId) {
        UserRatings userRatings = userRatingService.getUserRatings(userId);

        List<CatalogItem> catalogItemList = userRatings
                .getRatings()
                .stream()
                .map(rating -> movieCatalogService.getCatalogItem(rating))
                .collect(Collectors.toList());

        return new ResponseEntity(userId, catalogItemList);
    }
}
