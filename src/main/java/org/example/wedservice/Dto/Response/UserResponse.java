package org.example.wedservice.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Entity.Orders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    String avatar;
    String address;
    String district;
    String city;
    LocalDate dob;
    Set<RoleResponse> roles;
    List<OrderResponse> orders;
    LocalDateTime createdat;
    LocalDateTime updatedat;
    LocalDateTime deletedat;
    boolean isdeleted;
}
