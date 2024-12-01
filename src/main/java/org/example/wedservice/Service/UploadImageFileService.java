package org.example.wedservice.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.ImageResponse;
import org.example.wedservice.Entity.Image;
import org.example.wedservice.Repository.ImageRepository;
import org.example.wedservice.Repository.UploadImageFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UploadImageFileService implements UploadImageFile {
    Cloudinary cloudinary;
    ImageRepository imageRepository;
    @Override
    public String uploadImage(MultipartFile image) throws IOException {
        assert image.getOriginalFilename() != null;
        String publicValue=generatePublicValue(image.getOriginalFilename());
        String extension = getFileName(image.getOriginalFilename())[1];
        File fileupload=conver(image);
        log.info("File Upload Is:"+fileupload.toString());
        log.info("Public Value Is:"+publicValue.toString());
        log.info("extension Is:"+extension.toString());
        cloudinary.uploader().upload(fileupload, ObjectUtils.asMap("public_id",publicValue ));
        String filepatch=cloudinary.url().generate(StringUtils.join(publicValue+"."+extension));
        cleanDisk(fileupload);
        return imageRepository.save(Image.builder()
                .publicId(publicValue)
                .urlImage(filepatch)
                .build()).getIdimage();
    }
    private File conver(MultipartFile file) {
        assert file.getOriginalFilename() != null;
        File conVertFile = new File(StringUtils.join(generatePublicValue(file.getOriginalFilename()), ".", getFileName(file.getOriginalFilename())[1]));
        try(InputStream is=file.getInputStream()) {
            Files.copy(is,conVertFile.toPath());
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return conVertFile;
    }
    public String generatePublicValue(String originalFilename){
        String[] split = getFileName(originalFilename);
        return StringUtils.join(UUID.randomUUID().toString() + "." + split[0]);
    }
    public String[] getFileName(String filename){
        return filename.split("\\.");
    }
    public void cleanDisk(File file){
        try {
            log.info(file.toPath().toString());
            Path filePath = file.toPath();
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
