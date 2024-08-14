package com.example.library_management_system.Controllers;

import com.example.library_management_system.Models.Book;
import com.example.library_management_system.Models.BorrowingRecord;
import com.example.library_management_system.Models.Patron;
import com.example.library_management_system.Services.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/borrowing-record")
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @RequestMapping("/borrow-book")
    public ResponseEntity<String> borrowBook(Book book, Patron patron) {
         BorrowingRecord borrowingRecord = borrowingRecordService.borrowBook(book, patron);

        if (borrowingRecord == null) {
            return ResponseEntity.badRequest().body("Book is not available");
        }
        return ResponseEntity.ok("Book borrowed successfully");

    }

    @RequestMapping("/return-book")
    public ResponseEntity<String> returnBook(@RequestParam Long bookId,@RequestParam Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordService.findBorrowingRecordByBookAndPatron(bookId, patronId).get();
        borrowingRecordService.returnBook(borrowingRecord.getBorrowingRecordId());
        return ResponseEntity.ok("Book returned successfully");
    }


}
