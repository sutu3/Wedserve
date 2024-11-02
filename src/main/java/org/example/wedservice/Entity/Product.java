package org.example.wedservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Dto.Response.MaterialResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String idproduct;
    String name;
    @ManyToOne
    @JoinColumn(name = "idmaterial",nullable = false)
    Material materials;
    LocalDate createat;
    LocalDate deleteat;
    LocalDate updateat;
    boolean isdeleted=false;
}
