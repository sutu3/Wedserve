package org.example.wedservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class VersionProductResponse {
    String idversion;
    int quantity_in_stock;
    BigDecimal originalprice;
    BigDecimal selling_price;
    VarientNoListResponse varient;
    ProductPurchaseItemResponse product;
    LocalDateTime createat;
    LocalDateTime updateat;
    LocalDateTime deleteat;
    boolean isdeleted;

}
