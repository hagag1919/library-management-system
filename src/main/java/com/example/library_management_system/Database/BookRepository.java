package com.example.library_management_system.Database;

import com.example.library_management_system.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

