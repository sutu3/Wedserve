package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size,String> {
    boolean existsBySizename(String Sizename);
    Size findBySizename(String sizename);
    boolean existsBySize(String size);
}
