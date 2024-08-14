package com.example.library_management_system.Services;

import com.example.library_management_system.Database.PatronRepository;
import com.example.library_management_system.Models.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    public void savePatron(Patron patron) {
        patronRepository.save(patron);
    }

    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }

    public void updatePatron(Long id, Patron patron) {
        if (patronRepository.existsById(id)) {
            patronRepository.save(patron);
        }
    }
}

