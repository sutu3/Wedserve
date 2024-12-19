package org.example.wedservice.Dto.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Entity.Purchase;
import org.example.wedservice.Enum.StatusPurchase;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Purchase_ItemRequest implements Serializable {
    int quantity;
    String idpurchase;
    String color;
    String size;
    String productname;
}
