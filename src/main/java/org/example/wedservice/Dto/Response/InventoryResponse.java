package org.example.wedservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Entity.Order_Item;
import org.example.wedservice.Entity.Version;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class InventoryResponse {
    String idinventory;
    int change_amount;
    String event;
    VersionNoListResponse version;
    OrderItemResponse orderitem;
    LocalDateTime event_date;
    LocalDateTime createat;
    LocalDateTime updateat;
    boolean isdeleted;
}
