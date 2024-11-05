package org.example.wedservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Purchase_ItemResponse implements Serializable {
    String id;
    int quantity;
    String idpurchase;
    PurchaseResponseNoListItem purchase;
    BigDecimal totalprice;
    BigDecimal purchaseprice;
    LocalDateTime createat;
    LocalDateTime updateat;
}
