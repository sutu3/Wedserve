package org.example.wedservice.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserResponse {
    String id;
    String username;
    String password;
    String email;
    String phonenumber;
    String fullname;
    String gender;
    LocalDate dob;
    Set<RoleResponse> roles;
    LocalDateTime createdat;
    LocalDateTime updatedat;
    LocalDateTime deletedat;
    boolean isdeleted;
}
