package com.example.library_management_system.Services.IServices;

import com.example.library_management_system.Models.Book;
import com.example.library_management_system.Models.BorrowingRecord;
import com.example.library_management_system.Models.Patron;

import java.util.Optional;

public interface IBorrowingRecordServices {
    public BorrowingRecord borrowBook(Book book, Patron patron);
    public BorrowingRecord returnBook(Long borrowingRecordId);
    public Optional<BorrowingRecord> findBorrowingRecordByBookAndPatron(Long bookId, Long patronId);
}
