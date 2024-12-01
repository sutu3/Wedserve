package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Response.ImageResponse;
import org.example.wedservice.Dto.Response.MaterialResponse;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Mapper.ImageMapper;
import org.example.wedservice.Repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ImageService {
    ImageMapper imageMapper;
    ImageRepository imageRepository;
    public List<ImageResponse> getall(){
        return imageRepository.findAll().stream().map(imageMapper::toImageResponse)
                .collect(Collectors.toList());
    }
    public ImageResponse Getbyid(String id){
        return imageMapper.toImageResponse(imageRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.IMAGE_NOT_FOUND)));
    }
}
