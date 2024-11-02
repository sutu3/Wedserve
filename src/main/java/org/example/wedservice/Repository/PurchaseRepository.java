package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,String> {
}
