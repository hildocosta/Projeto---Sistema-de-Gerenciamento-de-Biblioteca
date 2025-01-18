package com.hildo.costa.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {

    @Id
    private String id;          // Identificador único
    private String title;       // Título do livro
    private String author;      // Autor do livro
    private String genre;       // Gênero literário
    private double price;       // Preço do livro


    // Construtor padrão
    public Book() {
    }

    // Construtor completo
    public Book(String id, double price, String genre, String author, String title) {
        this.id = id;
        this.price = price;
        this.genre = genre;
        this.author = author;
        this.title = title;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
