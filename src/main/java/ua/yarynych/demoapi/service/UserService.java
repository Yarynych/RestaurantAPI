package ua.yarynych.demoapi.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.yarynych.demoapi.dto.UserRegistrationDto;
import ua.yarynych.demoapi.model.User;


public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
}