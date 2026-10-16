package com.example.bookarchive;

public class Book {
    private String title;
    private String author;
    private String datePublished;
    private String category;

    public Book(String title, String author, String datePublished, String category) {
        this.title = title;
        this.author = author;
        this.datePublished = datePublished;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public String getCategory() {
        return category;
    }
}
