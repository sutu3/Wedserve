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
public class OrderResponse implements Serializable {
    String idorder;
    String status;
    LocalDateTime orderdate;
    BigDecimal totalamount;
    LocalDateTime createdat;
    LocalDateTime updatedat;
    LocalDateTime deletedat;
    boolean isDeleted;
}
