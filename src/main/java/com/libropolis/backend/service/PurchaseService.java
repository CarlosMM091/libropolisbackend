package com.libropolis.backend.service;

import com.libropolis.backend.model.Purchase;
import com.libropolis.backend.model.Book;
import com.libropolis.backend.model.User;
import com.libropolis.backend.repository.PurchaseRepository;
import com.libropolis.backend.repository.BookRepository;
import com.libropolis.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public List<Purchase> getPurchasesByUser(User user) {
        return purchaseRepository.findByUser(user);
    }

    // Save purchase
    public Purchase savePurchase(Purchase purchase) throws Exception {
        Optional<User> userOptional = userRepository.findById(purchase.getUser().getId());
        Optional<Book> bookOptional = bookRepository.findById(purchase.getBook().getId());

        if (!userOptional.isPresent()) {
            throw new Exception("User not found");
        }

        if (!bookOptional.isPresent()) {
            throw new Exception("Book not found");
        }

        User user = userOptional.get();
        Book book = bookOptional.get();

        if (book.getStock() < purchase.getQuantity()) {
            throw new Exception("Insufficient stock for the book: " + book.getTitle());
        }

        double totalPurchase = book.getPrice() * purchase.getQuantity();

        if (user.getBalance() < totalPurchase) {
            throw new Exception("Insufficient balance to complete the purchase");
        }

        // Update user balance and book stock
        user.setBalance(user.getBalance() - totalPurchase);
        book.setStock(book.getStock() - purchase.getQuantity());

        userRepository.save(user);
        bookRepository.save(book);

        // Assign full data and total to the purchase
        purchase.setUser(user);
        purchase.setBook(book);
        purchase.setTotal(totalPurchase);

        return purchaseRepository.save(purchase);
    }

    // Get all purchases
    public List<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }

    // Get purchase by ID
    public Optional<Purchase> getPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    // Delete purchase
    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }
}
