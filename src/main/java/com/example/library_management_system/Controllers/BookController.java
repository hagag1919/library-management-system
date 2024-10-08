package com.example.library_management_system.Controllers;

import com.example.library_management_system.Models.Book;
import com.example.library_management_system.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> addBook(Book newBook) {
        if (bookService.getBookById(newBook.getBookId()).isPresent()) {
            return ResponseEntity.badRequest().body("Book already exists");
        }
        bookService.addBook(newBook);
        return ResponseEntity.ok("Book added successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<String> updateBook(@RequestParam Long id, Book book) {
        if (bookService.getBookById(id).isEmpty()) {
            return ResponseEntity.badRequest().body("Book does not exist");
        }
        bookService.updateBook(id, book);
        return ResponseEntity.ok("Book updated successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestParam Long id) {
        if (bookService.getBookById(id).isEmpty()) {
            return ResponseEntity.badRequest().body("Book does not exist");
        }
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/getAll")
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/get")
    public ResponseEntity<Book> getBook(@RequestParam Long id) {
        return ResponseEntity.ok(bookService.getBookById(id).get());
    }

}
