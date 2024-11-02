package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender,String> {
    boolean existsByGender(String gendername);
}
