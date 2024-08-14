package com.example.library_management_system.Services;

import com.example.library_management_system.Database.PatronRepository;
import com.example.library_management_system.Models.Patron;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BorrowingRecordServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronService patronService;

    @Test
    public void testGetAllPatrons_empty() {
        List<Patron> emptyList = Collections.emptyList();
        when(patronRepository.findAll()).thenReturn(emptyList);

        List<Patron> patrons = patronService.getAllPatrons();

        assertEquals(0, patrons.size());
    }

    @Test
    public void testGetAllPatrons_withData() {
        List<Patron> patrons = new ArrayList<>();
        patrons.add(new Patron(1L,"Ahmed Hagag", "hagag@example.com","0111111"));
        patrons.add(new Patron(2L,"mo Hagag", "mohagag@example.com","0111111"));

        when(patronRepository.findAll()).thenReturn(patrons);

        List<Patron> fetchedPatrons = patronService.getAllPatrons();

        assertEquals(2, fetchedPatrons.size());
        assertEquals(patrons, fetchedPatrons); // Check for object equality
    }

    @Test
    public void testGetPatronById_existing() {
        Patron patron = new Patron(1L,"Ahmed Hagag", "hagag@example.com","0111111");
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));

        Optional<Patron> fetchedPatron = patronService.getPatronById(1L);

        assertTrue(fetchedPatron.isPresent());
        assertEquals(patron, fetchedPatron.get());
    }

    @Test
    public void testGetPatronById_nonExisting() {
        when(patronRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Patron> fetchedPatron = patronService.getPatronById(1L);

        assertFalse(fetchedPatron.isPresent());
    }

    @Test
    public void testSavePatron() {
        Patron patron = new Patron(1L,"Ahmed Hagag", "hagag@example.com","0111111");

        patronService.savePatron(patron);

        verify(patronRepository).save(patron);
    }

    @Test
    public void testDeletePatron() {
        patronService.deletePatron(1L);

        verify(patronRepository).deleteById(1L);
    }

    @Test
    public void testUpdatePatron_existing() {
        Patron existingPatron =new Patron(1L,"Ahmed Hagag", "hagag@example.com","0111111");
        when(patronRepository.existsById(1L)).thenReturn(true);

        Patron updatedPatron = new Patron(1L,"mo Hagag", "hagagt@example.com","0111114");
        patronService.updatePatron(1L, updatedPatron);

        verify(patronRepository).save(updatedPatron);
    }

    @Test
    public void testUpdatePatron_nonExisting() {
        Patron updatedPatron = new Patron(1L, "Ahmed Hagag", "hagag@example.com", "0111111");
        when(patronRepository.existsById(1L)).thenReturn(false);

        patronService.updatePatron(1L, updatedPatron);

        verify(patronRepository).save(updatedPatron);
    }
}
