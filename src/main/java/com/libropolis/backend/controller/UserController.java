package com.libropolis.backend.controller;

import com.libropolis.backend.model.User;
import com.libropolis.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> user = userService.getById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        User user = userService.getByEmail(email);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> userOpt = userService.getById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User existingUser = userOpt.get();
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setBalance(updatedUser.getBalance());
        User updated = userService.saveUser(existingUser);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{id}/recharge")
    public ResponseEntity<User> rechargeBalance(@PathVariable Long id, @RequestParam double amount) {
        if (amount < 50000 || amount > 200000) {
            return ResponseEntity.badRequest().build();
        }
        Optional<User> userOpt = userService.getById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOpt.get();
        user.setBalance(user.getBalance() + amount);
        User updated = userService.saveUser(user);
        return ResponseEntity.ok(updated);
    }
}
