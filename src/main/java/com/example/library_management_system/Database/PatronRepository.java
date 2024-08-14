package com.example.library_management_system.Database;

import com.example.library_management_system.Models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}
