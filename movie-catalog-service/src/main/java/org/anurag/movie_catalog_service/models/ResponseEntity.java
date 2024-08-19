package org.anurag.movie_catalog_service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity {

    private String userId;

    private List<CatalogItem> catalogItemList;

}
