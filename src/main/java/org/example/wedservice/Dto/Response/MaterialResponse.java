package org.example.wedservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaterialResponse implements Serializable {
    String id;
    String name;
    String durability;
    LocalDateTime createat;
    LocalDateTime deleteat;
    LocalDateTime updateat;
    boolean isdeleted;
}
