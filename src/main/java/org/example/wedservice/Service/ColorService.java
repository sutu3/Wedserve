package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.ColorRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.ColorResponse;
import org.example.wedservice.Entity.Color;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Color_Update;
import org.example.wedservice.Mapper.ColorMapper;
import org.example.wedservice.Repository.ColorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ColorService {
    private final ColorMapper colorMapper;
    ColorRepository repositoryColor;
    ColorMapper mappercolor;
    private final ColorRepository colorRepository;

    public ColorResponse getbyid(String id) throws AppException {
        return mappercolor.toColorResponse(repositoryColor.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.COLOR_NOT_FOUND)));
    }
    public List<ColorResponse> getall(){
        return repositoryColor.findAll().stream()
                .map(colorMapper::toColorResponse)
                .collect(Collectors.toList());
    }
    public ColorResponse PostColor(ColorRequest request) throws AppException {
        Color color=mappercolor.toColor(request);
        if(repositoryColor.existsByColorhex(request.getColorhex())
                ||repositoryColor.existsByColorname(request.getColorname())){
            throw new AppException(ErrorCode.COLOR_IS_EXITED);
        }
        return mappercolor.toColorResponse(repositoryColor.save(color));
    }
    public ColorResponse PutColor(String id,Color_Update update) throws AppException {
        Color color=repositoryColor.findById(id)
               .orElseThrow(()->new AppException(ErrorCode.COLOR_NOT_FOUND));
        mappercolor.updateColor(color,update);
        return mappercolor.toColorResponse(repositoryColor.save(color));
    }
    public void DeleteColor(String id) throws AppException {
        Color color=repositoryColor.findById(id)
               .orElseThrow(()->new AppException(ErrorCode.COLOR_NOT_FOUND));
        colorRepository.save(color.builder()
                .deleteat(LocalDateTime.now())
                .isdeleted(true)
                .build());
    }
}
