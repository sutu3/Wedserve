package org.example.wedservice.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryRequest {
    int change_amount;
    String orderitem;
    String productname;
    String colorname;
    String sizename;
}
