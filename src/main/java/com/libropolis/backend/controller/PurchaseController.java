package com.libropolis.backend.controller;

import com.libropolis.backend.model.Purchase;
import com.libropolis.backend.model.User;
import com.libropolis.backend.service.PurchaseService;
import com.libropolis.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
@CrossOrigin(origins = "*")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Purchase> getAll() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Purchase>> getByUser(@PathVariable Long userId) {
        Optional<User> userOpt = userService.getById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Purchase> purchases = purchaseService.getPurchasesByUser(userOpt.get());
        return ResponseEntity.ok(purchases);
    }

    @PostMapping
    public ResponseEntity<Object> savePurchase(@RequestBody Purchase purchase) {
        try {
            Purchase newPurchase = purchaseService.savePurchase(purchase);
            return ResponseEntity.ok(newPurchase);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving purchase: " + e.getMessage());
        }
    }
}
