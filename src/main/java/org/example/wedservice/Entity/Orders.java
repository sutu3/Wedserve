package org.example.wedservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String idorder;
    @ManyToOne
    @JoinColumn(name = "iduser",nullable = false)
    User user;
    String status;
    LocalDateTime orderdate;
    @OneToMany(mappedBy="order")
    List<Order_Item> orderitems;
    @Column(precision = 10, scale = 2)
    BigDecimal totalamount;
    LocalDateTime createdat;
    LocalDateTime updatedat;
    LocalDateTime deletedat;
    boolean isDeleted=false;

}
