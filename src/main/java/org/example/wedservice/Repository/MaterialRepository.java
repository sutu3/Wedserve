package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material,String> {
    boolean existsByName(String name);
    Material findFistByName(String name);
    Material findByName(String name);
}
