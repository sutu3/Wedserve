package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Varient;
import org.example.wedservice.Entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VarientRepository extends JpaRepository<Varient,String> {
    Varient findFirstByProduct_NameAndColor_colornameAndSize_Sizename(String name, String colorname,String sizename);
}
