package com.example.library_management_system.Services.IServices;

import com.example.library_management_system.Components.LoginDto;
import com.example.library_management_system.Components.RegisterDto;

public interface IAuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto loginDto);
}
