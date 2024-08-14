package com.example.library_management_system.Services;

import com.example.library_management_system.Database.PatronRepository;
import com.example.library_management_system.Models.Patron;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatronServiceTest {

    @Autowired
    private PatronService patronService;

    @Autowired
    private PatronRepository patronRepository;

    @BeforeEach
    public void setUp() throws Exception {
        patronRepository.deleteAll(); // Clear data before each test
    }

    @AfterEach
    public void tearDown() throws Exception {
        patronRepository.deleteAll(); // Clear data after each test
    }

    @Test
    public void testGetAllPatrons_empty() {
        List<Patron> patrons = patronService.getAllPatrons();
        assertEquals(0, patrons.size());
    }

    @Test
    public void testGetAllPatrons_withData() {
        // Create and save some patrons
        patronRepository.save(new Patron(1L,"Ahmed Hagag", "hagag@example.com","0111111"));
        patronRepository.save(new Patron(2L,"Mohamed Hagag", "Mohagag@example.com","0111111"));


        List<Patron> patrons = patronService.getAllPatrons();
        assertEquals(2, patrons.size());
    }

    @Test
    public void testGetPatronById_existing() {
        Patron patron = patronRepository.save(new Patron(1L,"John Doe", "johndoe@example.com","010000"));

        Optional<Patron> fetchedPatron = patronService.getPatronById(patron.getPatronId());
        assertTrue(fetchedPatron.isPresent());
        assertEquals(patron, fetchedPatron.get());
    }

    @Test
    public void testGetPatronById_nonExisting() {
        Optional<Patron> fetchedPatron = patronService.getPatronById(1L); // Assuming no patron with ID 1
        assertFalse(fetchedPatron.isPresent());
    }

    @Test
    public void testSavePatron() {
        Patron patron = new Patron(1L,"John Doe", "johndoe@example.com","010000");
        patronService.savePatron(patron);

        Optional<Patron> savedPatron = patronRepository.findById(patron.getPatronId());
        assertTrue(savedPatron.isPresent());
        assertEquals(patron, savedPatron.get());
    }

    @Test
    public void testDeletePatron() {
        Patron patron = patronRepository.save(new Patron(1L,"John Doe", "johndoe@example.com","010000"));

        patronService.deletePatron(patron.getPatronId());

        Optional<Patron> deletedPatron = patronRepository.findById(patron.getPatronId());
        assertFalse(deletedPatron.isPresent());
    }

    @Test
    public void testUpdatePatron_existing() {
        Patron patron = patronRepository.save(new Patron(1L,"John Doe", "johndoe@example.com","010000"));
        patron.setName("mo Hagag");

        patronService.updatePatron(patron.getPatronId(), patron);

        Optional<Patron> updatedPatron = patronRepository.findById(patron.getPatronId());
        assertTrue(updatedPatron.isPresent());
        assertEquals("mo Hagag", updatedPatron.get().getName());
    }

    @Test
    public void testUpdatePatron_nonExisting() {
        Patron patron = new Patron(3L,"John Doe", "johndoe@example.com","010000");

        patronService.updatePatron(1L, patron); // Assuming no patron with ID 1

        Optional<Patron> updatedPatron = patronRepository.findById(1L);
        assertFalse(updatedPatron.isPresent());
    }
}
