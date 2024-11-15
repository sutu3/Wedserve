package org.example.wedservice.Dto.Request;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class VarientRequest {
    String namevarient;
    String colorname;
    String idimage;
    String sizename;
    String categoryname;
    String idproduct;
}
