package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Category;
import org.example.wedservice.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,String> {
}
