package com.libropolis.backend.service;

import com.libropolis.backend.model.User;
import com.libropolis.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateBalance(Long userId, double rechargeAmount) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (rechargeAmount < 50000 || rechargeAmount > 200000) {
                throw new Exception("The recharge amount must be between $50,000 and $200,000.");
            }
            user.setBalance(user.getBalance() + rechargeAmount);
            return userRepository.save(user);
        } else {
            throw new Exception("User not found.");
        }
    }

    public User updateUserData(Long userId, User newUserData) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(newUserData.getName());
            user.setEmail(newUserData.getEmail());
            return userRepository.save(user);
        } else {
            throw new Exception("User not found.");
        }
    }
}
