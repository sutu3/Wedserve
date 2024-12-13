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
public class Inventory {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String idinventory;
    int change_amount;
    String event;
    @ManyToOne
    @JoinColumn(name = "idversion",nullable = false)
    Version version;
    @OneToOne(mappedBy = "inventory")
    Order_Item orderitem;
    LocalDateTime event_date;
    LocalDateTime createat;
    LocalDateTime updateat;
    boolean isdeleted=false;
}
