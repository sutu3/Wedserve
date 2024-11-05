package org.example.wedservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String iddescription;
    String title;
    String description;
    LocalDateTime deleteat;
    LocalDateTime updateat;
    boolean isdeleted=false;
}
