package com.example.borrowdb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "library")
public class Books {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int book_id;
        private String book_name;
        private String author;
        private int count;





    public Books() {

    }

    public Books(int book_id, int count, String author, String book_name) {
        this.book_id = book_id;
        this.count = count;
        this.author = author;
        this.book_name = book_name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}



