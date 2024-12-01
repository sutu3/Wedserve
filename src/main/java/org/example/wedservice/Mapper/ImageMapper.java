package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.CategoryRequest;
import org.example.wedservice.Dto.Response.CategoryResponse;
import org.example.wedservice.Dto.Response.ImageResponse;
import org.example.wedservice.Entity.Category;
import org.example.wedservice.Entity.Image;
import org.example.wedservice.Form.Category_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    /*Category toCategory(CategoryRequest request);
    @Mapping(source = "idcategory", target = "id")*/
    ImageResponse toImageResponse(Image image);
/*
    void updateCategory(@MappingTarget Category category, Category_Update update);
*/
}
