package org.anurag.movie_catalog_service.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.anurag.movie_catalog_service.models.CatalogItem;
import org.anurag.movie_catalog_service.models.Movie;
import org.anurag.movie_catalog_service.models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieCatalogService {




    // fallback method should be simple and hard coded
    // because if fallback method fails then we need another fallback

}
