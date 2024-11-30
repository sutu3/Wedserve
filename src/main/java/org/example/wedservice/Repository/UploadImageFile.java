package org.example.wedservice.Repository;

import org.example.wedservice.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
public interface UploadImageFile  {
    String uploadImage(MultipartFile image) throws IOException;
}
