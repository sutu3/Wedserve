package org.example.wedservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Enum.StatusPurchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Purchase {
    @Id
            @GeneratedValue(strategy= GenerationType.UUID)
    String idpurchase;
    @Enumerated(EnumType.STRING)
    StatusPurchase status;
    @Column(precision = 10, scale = 2)
    BigDecimal alamoung;
    @OneToMany(mappedBy="purchase")
    List<Purchase_Item> items;
    LocalDateTime createat;
    LocalDateTime updateat;
}
