package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Category;
import org.example.wedservice.Entity.InvalidateToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidateRepository extends JpaRepository<InvalidateToken,String> {
}
