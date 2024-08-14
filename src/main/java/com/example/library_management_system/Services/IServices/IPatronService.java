package com.example.library_management_system.Services.IServices;

import com.example.library_management_system.Models.Patron;

import java.util.List;
import java.util.Optional;

public interface IPatronService {
    public List<Patron> getAllPatrons();
    public Optional<Patron> getPatronById(Long id);
    public void savePatron(Patron patron);
    public void deletePatron(Long id);
    public void updatePatron(Long id, Patron patron);
}
