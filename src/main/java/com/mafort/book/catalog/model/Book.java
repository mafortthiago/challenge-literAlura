package com.mafort.book.catalog.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private Author author;
    private String language;
    private int downloads;

    public Book(BookRecord bookRecord) {
        this.title =  bookRecord.title();
        this.language = bookRecord.languages().get(0);
        this.downloads = bookRecord.downloads();
        this.author = new Author(bookRecord.authors().stream().findFirst().get());
    }
    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguages() {
        return language;
    }

    public void setLanguages(String language) {
        this.language = language;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "Book name: " + this.getTitle() + ", author: " + this.getAuthor();
    }
}
