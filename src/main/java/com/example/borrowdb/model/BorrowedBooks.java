package com.example.borrowdb.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "borrowed_books")
public class BorrowedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrow_id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Books book;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    private Date borrow_date;
    private Date return_date;

    public BorrowedBooks() {
    }

    public BorrowedBooks(Books book, User student, Date borrow_date, Date return_date) {
        this.book = book;
        this.student = student;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
    }

    // Getters and setters...

    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }


}
