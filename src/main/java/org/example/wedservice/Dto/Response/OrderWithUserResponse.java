package org.example.wedservice.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderWithUserResponse implements Serializable {
    String idorder;
    String status;
    LocalDateTime orderdate;
    BigDecimal totalamount;
    List<ShippingResponse> shippings;
    List<OrderItemResponse> orderitems;
    UserResponseNoList user;
    LocalDateTime createdat;
    LocalDateTime updatedat;
    LocalDateTime deletedat;
    boolean isDeleted;
}
