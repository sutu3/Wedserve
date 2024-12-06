package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.ImageResponse;
import org.example.wedservice.Dto.Response.UserResponse;
import org.example.wedservice.Repository.UploadImageFile;
import org.example.wedservice.Service.ImageService;
import org.example.wedservice.Service.UploadImageFileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@CrossOrigin(origins = {"http://localhost:5173","http://26.144.191.229:5173","http://26.225.63.179:5173"})
public class UploadFileController {
    UploadImageFileService uploadFile;
    ImageService imageService;
    @PostMapping("/upload")
    public ApiResponse<ImageResponse> uploadFileImage(@RequestParam("file") MultipartFile file) throws IOException {
        String idImage=uploadFile.uploadImage(file);
        return ApiResponse.<ImageResponse>builder()
                .Result(imageService.Getbyid(idImage))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
}
