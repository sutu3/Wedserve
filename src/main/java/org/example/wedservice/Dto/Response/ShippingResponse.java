package org.example.wedservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Entity.Orders;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Builder
public class ShippingResponse {
    String idshipping;
    String status;
    LocalDateTime shippingdate;
    LocalDateTime createdat;
    LocalDateTime updatedat;
}
