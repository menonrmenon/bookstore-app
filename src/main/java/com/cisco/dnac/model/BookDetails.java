package com.cisco.dnac.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.concurrent.atomic.AtomicInteger;

@Document(collection = "bookdetails")
public class BookDetails {
    @Id
    private int id;

    @Indexed(unique = true)
    private String name;

    private String author;

    @Indexed(unique = true)
    private String isbn;

    private String genre;

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    public BookDetails() {
    }

    public BookDetails(String name, String author, String isbn, String genre) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
