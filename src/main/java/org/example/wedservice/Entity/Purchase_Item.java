package org.example.wedservice.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Purchase_Item {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String idpurchaseitem;
    int quantity;
    @ManyToOne
    @JoinColumn(name = "idpurchase",nullable = false)
    Purchase purchase;
    @Column(precision = 10, scale = 2)
    BigDecimal totalprice;
    @Column(precision = 10, scale = 2)
    BigDecimal purchaseprice;
    LocalDateTime createat;
    LocalDateTime updateat;
}
