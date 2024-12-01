package org.example.wedservice.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductWithImageRequest implements Serializable {
    String idproduct;
    String idImage;
}
