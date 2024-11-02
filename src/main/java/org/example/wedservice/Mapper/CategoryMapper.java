package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.CategoryRequest;
import org.example.wedservice.Dto.Response.CategoryResponse;
import org.example.wedservice.Entity.Category;
import org.example.wedservice.Form.Category_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);
    @Mapping(source = "idcategory", target = "id")
    CategoryResponse toCategoryResponse(Category category);
    void updateCategory(@MappingTarget Category category, Category_Update update);
}
