package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.CategoryRequest;
import org.example.wedservice.Dto.Request.MaterialRequest;
import org.example.wedservice.Dto.Response.CategoryResponse;
import org.example.wedservice.Dto.Response.MaterialResponse;
import org.example.wedservice.Entity.Category;
import org.example.wedservice.Entity.Material;
import org.example.wedservice.Form.Category_Update;
import org.example.wedservice.Form.Material_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    Material toMaterial(MaterialRequest request);
    @Mapping(source = "idmaterial", target = "id") // Ánh xạ idMaterial của entity với id của DTO
    MaterialResponse toMaterialResponse(Material material);
    void updateMaterial(@MappingTarget Material material, Material_Update update);
}
