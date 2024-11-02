package org.example.wedservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Dto.Response.ProductResponse;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String idmaterial;
    String name;
    @OneToMany
    Set<Product> products;
    String durability;
    LocalDate createat;
    LocalDate deleteat;
    LocalDate updateat;
    boolean isdeleted=false;
}
