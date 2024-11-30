package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Repository.UploadImageFile;
import org.example.wedservice.Service.UploadImageFileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@CrossOrigin(origins = {"http://localhost:5173","http://26.144.191.229:5173","http://26.225.63.179:5173"})
public class UploadFileController {
    UploadImageFileService uploadFile;
    @PostMapping("/upload")
    public String uploadFileImage(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = uploadFile.uploadImage(file);
        return "File uploaded successfully: " + fileName;
    }
}
