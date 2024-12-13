package org.example.wedservice.Repository;

import org.example.wedservice.Entity.InvalidateToken;
import org.example.wedservice.Entity.Inventory;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,String> {
}
