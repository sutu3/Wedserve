package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    boolean existsByName(String name);
    Category findByName(String name);
}
