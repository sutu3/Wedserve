package org.example.wedservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Dto.Response.MaterialResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @OneToMany(mappedBy="product")
    List<Version> version;
    @OneToMany(mappedBy="product")
    List<Varient> varients;
    @ManyToOne
    @JoinColumn(name = "idmaterial",nullable = false)
    Material materials;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idimage", referencedColumnName = "idimage")
    Image image;
    @ManyToOne
    @JoinColumn(name = "idgender",nullable = false)
    Gender gender;
    @ManyToOne
    @JoinColumn(name = "idcategory",nullable = false)
    Category category;
    @OneToMany(mappedBy="product")
    List<Description> descriptions;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcategory", referencedColumnName = "idcategory")
    Category category;*/
    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idgender", referencedColumnName = "idgender")
    Gender gender;*/
    LocalDateTime createat;
    LocalDateTime deleteat;
    LocalDateTime updateat;
    boolean isdeleted=false;
}
