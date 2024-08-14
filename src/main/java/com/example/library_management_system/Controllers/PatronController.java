package com.example.library_management_system.Controllers;

import com.example.library_management_system.Models.Patron;
import com.example.library_management_system.Services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @RequestMapping("/add")
    public ResponseEntity<String> addPatron(Patron newPatron) {
        if (patronService.getPatronById(newPatron.getPatronId()).isPresent()) {
            return ResponseEntity.badRequest().body("Patron already exists");
        }
        patronService.savePatron(newPatron);
        return ResponseEntity.ok("Patron added successfully");
    }

    @RequestMapping("/update")
    public ResponseEntity<String> updatePatron(@RequestParam Long id, Patron updatedPatron) {
        if (patronService.getPatronById(id).isEmpty()) {
            return ResponseEntity.badRequest().body("Patron does not exist");
        }
        patronService.updatePatron(id, updatedPatron);
        return ResponseEntity.ok("Patron updated successfully");
    }

    @RequestMapping("/delete")
    public ResponseEntity<String> deletePatron(@RequestParam Long id) {
        if (patronService.getPatronById(id).isEmpty()) {
            return ResponseEntity.badRequest().body("Patron does not exist");
        }
        patronService.deletePatron(id);
        return ResponseEntity.ok("Patron deleted successfully");
    }

    @RequestMapping("/get")
    public ResponseEntity<Patron> getPatron(@RequestParam Long id) {
        return ResponseEntity.ok(patronService.getPatronById(id).orElse(null));
    }

    @RequestMapping("/getall")
    public ResponseEntity<Iterable<Patron>> getAllPatrons() {
        return ResponseEntity.ok(patronService.getAllPatrons());
    }
}
