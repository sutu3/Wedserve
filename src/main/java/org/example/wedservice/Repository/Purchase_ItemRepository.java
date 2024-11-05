package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Purchase;
import org.example.wedservice.Entity.Purchase_Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Purchase_ItemRepository extends JpaRepository<Purchase_Item,String> {
}
