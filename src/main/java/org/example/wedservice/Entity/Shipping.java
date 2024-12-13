package org.example.wedservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class Shipping {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String idshipping;
    String status;
    @ManyToOne
    @JoinColumn(name = "idorder",nullable = false)
    Orders order;
    LocalDateTime shippingdate;
    LocalDateTime createdat;
    LocalDateTime updatedat;
}
