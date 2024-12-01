package org.example.wedservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Entity.Varient;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse implements Serializable {
    String id;
    String name;
    MaterialResponse materials;
    CategoryResponse category;
    GenderResponse gender;
    List<VarientResponse> varients;
    List<DescriptionResponse> descriptions;
    ImageResponse image;
    LocalDateTime createat;
    LocalDateTime deleteat;
    LocalDateTime updateat;
    boolean isdeleted;
}
