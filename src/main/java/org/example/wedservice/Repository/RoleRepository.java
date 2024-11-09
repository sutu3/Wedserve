package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Permission;
import org.example.wedservice.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    boolean existsByName(String name);
}
