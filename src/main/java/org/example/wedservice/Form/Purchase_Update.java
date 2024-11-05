package org.example.wedservice.Form;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Dto.Response.Purchase_ItemResponse;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase_Update {
    Double totalamoung;
    String status;
    Set<Purchase_ItemResponse> items;
}
