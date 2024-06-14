package com.mafort.book.catalog.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record BookRecord (
        @JsonAlias("title")
        String title,
        @JsonAlias("authors")
        List<AuthorRecord> authors,
        @JsonAlias("languages")
        List<String> languages,
        @JsonAlias("download_count")
        int downloads
){
}