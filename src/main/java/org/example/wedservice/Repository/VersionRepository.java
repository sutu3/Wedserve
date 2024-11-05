package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Purchase_Item;
import org.example.wedservice.Entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<Version,String> {
}
