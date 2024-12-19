package org.example.wedservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class VarientNoListResponse {
    String idvarient;
    String namevarient;
    ColorResponse color;
    SizeResponse size;
    LocalDateTime updateat;
    LocalDateTime deleteat;
    boolean isdeleted;

}
