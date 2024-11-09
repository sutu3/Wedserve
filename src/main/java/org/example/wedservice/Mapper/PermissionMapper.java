package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.PermissionRequest;
import org.example.wedservice.Dto.Request.ProductRequest;
import org.example.wedservice.Dto.Response.PermissionResponse;
import org.example.wedservice.Dto.Response.ProductResponse;
import org.example.wedservice.Entity.Permission;
import org.example.wedservice.Entity.Product;
import org.example.wedservice.Form.Product_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
