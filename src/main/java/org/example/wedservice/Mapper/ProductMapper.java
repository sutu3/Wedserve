package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.MaterialRequest;
import org.example.wedservice.Dto.Request.ProductRequest;
import org.example.wedservice.Dto.Response.MaterialResponse;
import org.example.wedservice.Dto.Response.ProductResponse;
import org.example.wedservice.Entity.Material;
import org.example.wedservice.Entity.Product;
import org.example.wedservice.Form.Material_Update;
import org.example.wedservice.Form.Product_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest request);
    @Mapping(source = "idproduct",target = "id")
    ProductResponse toProductResponse(Product product);
    void updateUpdate(@MappingTarget Product product, Product_Update update);
}
