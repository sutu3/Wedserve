package org.example.wedservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String idcategory;
    String name;
    @OneToMany(mappedBy="category")
    Set<Product> products;
    LocalDateTime createat;
    LocalDateTime deleteat;
    LocalDateTime updateat;
    boolean isdeleted=false;
}
