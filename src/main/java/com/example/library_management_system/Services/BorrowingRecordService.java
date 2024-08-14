package com.example.library_management_system.Services;

import com.example.library_management_system.Database.BorrowingRecordRepository;
import com.example.library_management_system.Models.Book;
import com.example.library_management_system.Models.BorrowingRecord;
import com.example.library_management_system.Models.Patron;
import com.example.library_management_system.Services.IServices.IBorrowingRecordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class BorrowingRecordService implements IBorrowingRecordServices {
    private final BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
    }

    @Override
    @Transactional
    public BorrowingRecord borrowBook(Book book, Patron patron) {

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setContactInfo(patron.getContactInfo());
        return borrowingRecordRepository.save(borrowingRecord);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BorrowingRecord> findBorrowingRecordByBookAndPatron(Long bookId, Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findById(bookId).get();

        if(Objects.equals(borrowingRecord.getPatron().getPatronId(), patronId)) {
            return Optional.of(borrowingRecord);
        } else {
            return Optional.empty();
        }

    }

    @Override
    @Transactional(readOnly = true)
    public BorrowingRecord returnBook(Long borrowingRecordId) {
        return borrowingRecordRepository.findById(borrowingRecordId).get();
    }
  }