package org.example.wedservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String iduser;
    String username;
    String password;
    String email;
    String avatar;
    String address;
    String district;
    String city;
    @ManyToMany
    Set<Role> roles;
    String phonenumber;
    String fullname;
    String gender;
    @OneToMany(mappedBy="user")
    List<Orders> orders;
    LocalDate dob;
    LocalDateTime createdat;
    LocalDateTime updatedat;
    LocalDateTime deletedat;
    boolean isDeleted;

}
