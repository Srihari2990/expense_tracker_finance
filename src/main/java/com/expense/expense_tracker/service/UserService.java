package com.expense.expense_tracker.service;

import com.expense.expense_tracker.model.User;
import com.expense.expense_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) throws Exception {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("Username is already taken!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER"); // Default role
        return userRepository.save(user);
    }

    // Additional user-related methods can be added here
}