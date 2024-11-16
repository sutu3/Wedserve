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
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor

public class Version {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String idversion;
    int quantity_in_stock;
    @Column(precision = 10, scale = 2)
    BigDecimal originalprice;
    @Column(precision = 10, scale = 2)
    BigDecimal selling_price;
    @ManyToOne
    @JoinColumn(name = "idproduct",nullable = false)
    Product product;
    @OneToMany(mappedBy="version")
    List<Purchase_Item> purchaseItem;
    @ManyToOne
    @JoinColumn(name = "idvarient",nullable = false)
    Varient varient;
    LocalDateTime createat;
    LocalDateTime updateat;
    LocalDateTime deleteat;
    boolean isdeleted=false;
}
