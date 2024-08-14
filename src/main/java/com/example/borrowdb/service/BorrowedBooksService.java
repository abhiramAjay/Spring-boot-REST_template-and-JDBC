package com.example.borrowdb.service;
import com.example.borrowdb.model.Books;
import com.example.borrowdb.model.BorrowedBooks;
import com.example.borrowdb.model.User;
import com.example.borrowdb.repository.BorrowedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class BorrowedBooksService {

    @Autowired
    private BorrowedBooksRepository borrowedBooksRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookReturnProducer bookReturnProducer; // Inject the producer service


    public ResponseEntity<BorrowedBooks> borrowBook(int bookId, int userId) {
        try {
            Books book = restTemplate.getForObject("http://localhost:8082/books/get-byid/" + bookId, Books.class);
            User user = restTemplate.getForObject("http://localhost:8081/users/get-byid/" + userId, User.class);

            if (book != null && user != null) {
                BorrowedBooks borrowedBook = new BorrowedBooks();
                borrowedBook.setBook(book);
                borrowedBook.setStudent(user);
                borrowedBook.setBorrow_date(new Date());
                borrowedBook.setReturn_date(null);

                borrowedBooksRepository.save(borrowedBook);

                return ResponseEntity.ok(borrowedBook);
            }

            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<BorrowedBooks> returnBook(int borrowId) {
        return borrowedBooksRepository.findById(borrowId)
                .map(borrowedBook -> {
                    borrowedBook.setReturn_date(new Date());
                    borrowedBooksRepository.save(borrowedBook);

                    // Get the book ID from the borrowed book
                    int bookId = borrowedBook.getBook().getBook_id();

                    // Send the book ID to the message queue
                    bookReturnProducer.sendMessage(String.valueOf(bookId));

                    return ResponseEntity.ok(borrowedBook);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public List<BorrowedBooks> getAllBorrowedBooks() {


    return borrowedBooksRepository.findAll();}

    public ResponseEntity<List<Books>> getBookById() {
        String url = "http://localhost:8082/books/get-all-books";

        ResponseEntity<List<Books>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Books>>() {}
        );

        return response;
    }
}

