package com.example.library_management_system.Services;

import com.example.library_management_system.Database.PatronRepository;
import com.example.library_management_system.Models.Patron;
import com.example.library_management_system.Services.IServices.IPatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;



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
    @Transactional(readOnly = true)
    @Cacheable("patrons")
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("patrons")
    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    @Override
    @Transactional
    public void savePatron(Patron patron) {
        patronRepository.save(patron);
    }

    @Override
    @Transactional
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updatePatron(Long id, Patron patron) {
        if (patronRepository.existsById(id)) {
            patronRepository.save(patron);
        }
    }
}

