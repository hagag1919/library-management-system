package com.example.library_management_system.Services.IServices;

import com.example.library_management_system.Models.Book;

import java.util.Optional;

public interface IBookService {
    public void addBook(Book book);
    public Optional<Book> getBookById(Long id);
    public Iterable<Book> getAllBooks();
    public void deleteBook(Long id);
    public void updateBook(Long id, Book book);
}
