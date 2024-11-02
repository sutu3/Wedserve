package org.example.wedservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
    String status;
    String totalamoung;
    LocalDate createat;
    LocalDate updateat;
}
