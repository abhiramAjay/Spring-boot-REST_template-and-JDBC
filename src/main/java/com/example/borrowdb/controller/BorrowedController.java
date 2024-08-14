package com.example.borrowdb.controller;

import com.example.borrowdb.model.Books;
import com.example.borrowdb.model.BorrowedBooks;
import com.example.borrowdb.service.BorrowedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowed-books")
public class BorrowedController {

    @Autowired
    private BorrowedBooksService borrowedBooksService;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowedBooks> borrowBook(@RequestParam int bookId, @RequestParam int userId) {
        return borrowedBooksService.borrowBook(bookId, userId);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Books>> getBookById() {

        return borrowedBooksService.getBookById();
    }

    @PostMapping("/return")
    public ResponseEntity<BorrowedBooks> returnBook(@RequestParam int borrowId) {
        return borrowedBooksService.returnBook(borrowId);
    }

    @GetMapping("/get-borrowed")
    public List<BorrowedBooks> getAllBorrowedBooks() {
        return borrowedBooksService.getAllBorrowedBooks();
    }
}
