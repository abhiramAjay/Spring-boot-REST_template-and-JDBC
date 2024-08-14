package com.example.borrowdb.repository;

import com.example.borrowdb.model.BorrowedBooks;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Integer> {
}
