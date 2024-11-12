package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.UserRequest;
import org.example.wedservice.Dto.Request.VersionRequest;
import org.example.wedservice.Dto.Response.UserResponse;
import org.example.wedservice.Dto.Response.VersionResponse;
import org.example.wedservice.Entity.User;
import org.example.wedservice.Entity.Version;
import org.example.wedservice.Form.User_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VersionMapper {
    Version toVersion(VersionRequest request);
    VersionResponse toVersionResponse(Version version);
    @Mapping(target = "roles",ignore = true)
    void updateUser(@MappingTarget User user, User_Update update);
}
