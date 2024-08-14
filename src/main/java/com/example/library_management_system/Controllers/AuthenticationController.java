package com.example.library_management_system.Controllers;

import com.example.library_management_system.Models.User;
import com.example.library_management_system.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    // register
    @PostMapping(value = "/registration")
    public ResponseEntity<String> createNewUser(User user, BindingResult bindingResult) {
        User userExists = userService.findUserByUserName(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Registration failed. Please check your input.");
        } else {
            userService.saveUser(user);
            return ResponseEntity.ok("User has been registered successfully");
        }
    }
}
