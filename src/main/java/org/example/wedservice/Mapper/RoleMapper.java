package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.PermissionRequest;
import org.example.wedservice.Dto.Request.RoleRequest;
import org.example.wedservice.Dto.Response.PermissionResponse;
import org.example.wedservice.Dto.Response.RoleResponse;
import org.example.wedservice.Entity.Permission;
import org.example.wedservice.Entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions",ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
