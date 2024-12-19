package org.example.wedservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductPurchaseItemResponse implements Serializable {
    String id;
    String name;
    MaterialResponse materials;
    CategoryResponse category;
    GenderResponse gender;
    List<VarientNoListResponse> varients;
    List<DescriptionResponse> descriptions;
    ImageResponse image;
    LocalDateTime createat;
    LocalDateTime deleteat;
    LocalDateTime updateat;
    boolean isdeleted;
}
