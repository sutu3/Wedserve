package org.example.wedservice.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CrosConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // TODO Auto-generated method stub
        registry.addMapping("/**")
                .allowedOrigins("http://26.144.191.229:5173","http://26.232.136.42:5173","http://localhost:8080","http://localhost:5173") // Thay thế bằng URL của frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Cho phép các phương thức HTTP cụ thể
                .allowedHeaders("*")
                .allowCredentials(true);

    }

}
