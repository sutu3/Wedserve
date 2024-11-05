package org.example.wedservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    int quantity_in_stock ;
    @Column(precision = 10, scale = 2)
    BigDecimal purchaseprice;
    @Column(precision = 10, scale = 2)
    BigDecimal saleprice;
    @ManyToOne
    @JoinColumn(name = "idproduct",nullable = false)
    Product product;
    LocalDateTime createat;
    LocalDateTime updateat;
    LocalDateTime deleteat;
    boolean isdeleted;

}
