package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Material;
import org.example.wedservice.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    boolean existsByName(String name);
}
