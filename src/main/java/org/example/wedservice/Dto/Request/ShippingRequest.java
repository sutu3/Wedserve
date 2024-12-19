package org.example.wedservice.Dto.Request;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Entity.Orders;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class ShippingRequest {
    String status;
    String order;
    LocalDateTime shippingdate;
}
