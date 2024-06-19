package com.mafort.book.catalog.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    @JsonAlias("results")
    private List<BookRecord> results;

    public List<BookRecord> getResults() {
        return this.results;
    }
}
