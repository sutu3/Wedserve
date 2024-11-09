package org.example.wedservice.Configuration;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Entity.User;
import org.example.wedservice.Enum.Role;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {


    PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            // Initial data setup
            if(userRepository.findByUsername("admin").isEmpty()) {
                HashSet<String> roles=new HashSet<String>();
                roles.add(Role.ADMIN.name());
                User user=User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
/*
                        .roles(roles)
*/
                        .createdat(LocalDateTime.now())
                        .isDeleted(false)
                        .build();
                userRepository.save(user);
            }

            log.warn("user admin created with default password username is admin");
            };
    }
}
