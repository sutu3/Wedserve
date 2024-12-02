package org.example.wedservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class Order_Item {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String idorderitem;
    String productname;
    String colorname;
    String sizename;
    @ManyToOne
    @JoinColumn(name = "idorder",nullable = false)
    Orders order;
    Integer quantity;
    @Column(precision = 10, scale = 2)
    BigDecimal price_at_sale;
    LocalDateTime createdat;
    LocalDateTime updatedat;
}
