package org.example.wedservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class VersionResponse{
    String idversion;
    int quantity_in_stock;
    BigDecimal originalprice;
    BigDecimal selling_price;
    ProductResponse product;
    List<Purchase_ItemResponse> purchaseItem;
    LocalDateTime createat;
    LocalDateTime updateat;
    LocalDateTime deleteat;
    boolean isdeleted;

}
