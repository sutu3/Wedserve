package org.example.wedservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor

public class Varient {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String idvariant;
    String namevarient;
    @ManyToOne
    @JoinColumn(name = "idcolor",nullable = false)
    Color color;
    @ManyToOne
    @JoinColumn(name = "idsize",nullable = false)
    Size size;
    @ManyToOne
    @JoinColumn(name = "idproduct",nullable = false)
    Product product;
    @OneToMany(mappedBy="varient")
    List<Version> versions;
    LocalDateTime createat;
    LocalDateTime updateat;
    LocalDateTime deleteat;
    boolean isdeleted=false;
}
