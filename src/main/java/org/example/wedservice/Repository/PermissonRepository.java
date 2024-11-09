package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Permission;
import org.example.wedservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissonRepository extends JpaRepository<Permission,String> {
    boolean existsByName(String name);
}
