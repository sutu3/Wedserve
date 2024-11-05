package org.example.wedservice.Dto.Response;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class VersionResponse{
    String idversion;
    int quantity_in_stock;
    BigDecimal purchaseprice;
    BigDecimal saleprice;
    ProductResponse product;
    LocalDateTime createat;
    LocalDateTime updateat;
    LocalDateTime deleteat;
    boolean isdeleted;

}
