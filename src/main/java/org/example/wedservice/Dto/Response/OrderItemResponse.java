package org.example.wedservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemResponse implements Serializable {
    String idorderitem;
    String productname;
    String colorname;
    String sizename;
    Integer quantity;
    BigDecimal price_at_sale ;
    LocalDateTime createdat;
    LocalDateTime updatedat;
}
