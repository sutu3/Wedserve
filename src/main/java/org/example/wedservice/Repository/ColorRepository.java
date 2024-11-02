package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color,String> {
    boolean existsByColorname(String colorname);
    boolean existsByColorhex(String colorhex);
}
