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
    String colorname;
    String sizename;
    String categoryname;
    BigDecimal originalprice;
    BigDecimal selling_price;
    String idproduct;
}