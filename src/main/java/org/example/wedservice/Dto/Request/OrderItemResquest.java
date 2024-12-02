package org.example.wedservice.Dto.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemResquest {
    String productname;
    String colorname;
    String sizename;
    String orderid;
    Integer quantity;
    BigDecimal price_at_sale ;
}
