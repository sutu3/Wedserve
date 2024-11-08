package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.PurchaseRequest;
import org.example.wedservice.Dto.Request.UserRequest;
import org.example.wedservice.Dto.Response.PurchaseResponse;
import org.example.wedservice.Dto.Response.PurchaseResponseNoListItem;
import org.example.wedservice.Dto.Response.UserResponse;
import org.example.wedservice.Entity.Purchase;
import org.example.wedservice.Entity.User;
import org.example.wedservice.Form.Purchase_Update;
import org.example.wedservice.Form.User_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);
    @Mapping(source = "iduser", target = "id") // Ánh xạ idMaterial của entity với id của DTO
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, User_Update update);
}
