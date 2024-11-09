package org.example.wedservice.Form;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Dto.Response.Purchase_ItemResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User_Update {
    String username;
    String password;
    String email;
    String phonenumber;
    String fullname;
    String gender;
    LocalDate dob;
    List<String> roles;
}
