package com.example.library_management_system.Models;

import jakarta.persistence.*;
@Entity
public class BorrowingRecord {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long borrowingRecordId;

        //Foreign Key
        @ManyToOne
        @JoinColumn(name = "book_id")
        private Book book;

        @ManyToOne
        @JoinColumn(name = "patron_id")
        private Patron patron;

        private String contactInfo;

        public BorrowingRecord(Long borrowingRecordId, Book book, Patron patron, String contactInfo) {
            this.borrowingRecordId = borrowingRecordId;
            this.book = book;
            this.patron = patron;
            this.contactInfo = contactInfo;

        }

        public BorrowingRecord() {
        }

        //Getters and Setters

        public void setBorrowingRecordId(Long borrowingRecordId) {
            this.borrowingRecordId = borrowingRecordId;
        }

        public void setBook(Book bookId) {
            this.book = bookId;
        }

        public void setPatron(Patron patronId) {
            this.patron = patronId;
        }

        public void setContactInfo(String contactInfo) {
            this.contactInfo = contactInfo;
        }

        public Long getBorrowingRecordId() {
            return borrowingRecordId;
        }

        public Book getBook() {
            return book;
        }

        public Patron getPatron() {
            return patron;
        }

        public String getContactInfo() {
            return contactInfo;
        }


}
