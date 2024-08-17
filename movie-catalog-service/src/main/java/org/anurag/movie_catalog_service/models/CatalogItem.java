package org.anurag.movie_catalog_service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogItem {

    private String movieName;
    private String description;
    private int rating;


}
