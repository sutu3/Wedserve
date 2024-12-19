package org.example.wedservice.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserResponseNoList {
    String iduser;
    String username;
    String email;
    String phonenumber;
    String fullname;
    String gender;
    String avatar;
    String address;
    String district;
    String city;

}
