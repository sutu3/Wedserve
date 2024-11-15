package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.VarientRequest;
import org.example.wedservice.Dto.Request.VersionRequest;
import org.example.wedservice.Dto.Response.VarientResponse;
import org.example.wedservice.Dto.Response.VersionResponse;
import org.example.wedservice.Entity.User;
import org.example.wedservice.Entity.Varient;
import org.example.wedservice.Entity.Version;
import org.example.wedservice.Form.User_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VarientMapper {

    Varient toVarient(VarientRequest request);
    VarientResponse toVarientResponse(Varient varient);
    @Mapping(target = "roles",ignore = true)
    void updateUser(@MappingTarget User user, User_Update update);
}
