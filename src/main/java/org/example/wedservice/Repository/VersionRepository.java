package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<Version,String> {
    Version findFirstByIsdeletedFalseAndVarient_Idvariant(String idVarient);
}
