package com.mafort.book.catalog.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Date;

public record AuthorRecord(@JsonAlias("name") String name,@JsonAlias("birth_year") int birthYear, @JsonAlias("death_year")int deathYear) {
}
