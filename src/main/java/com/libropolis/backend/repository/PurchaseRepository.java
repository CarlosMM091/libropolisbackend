package com.libropolis.backend.repository;

import com.libropolis.backend.model.Purchase;
import com.libropolis.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUser(User user);
}



