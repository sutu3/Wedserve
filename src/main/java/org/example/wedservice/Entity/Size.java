package org.example.wedservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String idsize;
    String size;
    String sizename;
    LocalDateTime createat;
    LocalDateTime updateat;
}
