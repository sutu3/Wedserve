package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.ColorRequest;
import org.example.wedservice.Dto.Response.ColorResponse;
import org.example.wedservice.Entity.Color;
import org.example.wedservice.Form.Color_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    Color toColor(ColorRequest request);
    @Mapping(source = "idcolor",target = "id")
    ColorResponse toColorResponse(Color color);
    void updateColor(@MappingTarget Color color, Color_Update update);
}
