package com.example.library_management_system.Services;

import com.example.library_management_system.Database.BookRepository;
import com.example.library_management_system.Models.Book;
import com.example.library_management_system.Services.IServices.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService implements IBookService{

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        
        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("books")
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("books")
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateBook(Long id, Book book) {
        if (bookRepository.existsById(id)) {
            bookRepository.save(book);
        }
    }
}