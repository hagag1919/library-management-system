package com.example.library_management_system.Services;

import com.example.library_management_system.Database.PatronRepository;
import com.example.library_management_system.Models.Patron;
import com.example.library_management_system.Services.IServices.IPatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService  implements IPatronService {
    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @Override
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Override
    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    @Override
    public void savePatron(Patron patron) {
        patronRepository.save(patron);
    }

    @Override
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }

    @Override
    public void updatePatron(Long id, Patron patron) {
        if (patronRepository.existsById(id)) {
            patronRepository.save(patron);
        }
    }
}

