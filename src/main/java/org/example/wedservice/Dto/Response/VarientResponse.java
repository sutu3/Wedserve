package org.example.wedservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Entity.Color;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class VarientResponse {
    String idvarient;
    String namevarient;
    ColorResponse color;
    SizeResponse size;
    CategoryResponse category;
    List<VersionNoListResponse> versions;
    LocalDateTime updateat;
    LocalDateTime deleteat;
    boolean isdeleted;

}
