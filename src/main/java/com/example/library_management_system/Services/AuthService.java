package com.example.library_management_system.Services;

import com.example.library_management_system.Components.LoginDto;
import com.example.library_management_system.Components.RegisterDto;
import com.example.library_management_system.Configuration.JwtTokenProvider;
import com.example.library_management_system.Database.UserRepository;
import com.example.library_management_system.Models.User;
import com.example.library_management_system.Services.IServices.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private UserRepository userRepository;
    @Autowired
    public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return JwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {
        // Check if the username or email already exists
        if (userRepository.findByUsernameOrEmail(registerDto.getUsername(), registerDto.getEmail()).isPresent()) {
            throw new RuntimeException("Username is already taken!");
        }

        // Encode the password
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());

        // Create a new user
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                registerDto.getUsername(),
                registerDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return JwtTokenProvider.generateToken(authentication);
    }

}
