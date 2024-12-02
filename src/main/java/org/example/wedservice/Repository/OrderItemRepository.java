package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Order_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<Order_Item,String> {

}
