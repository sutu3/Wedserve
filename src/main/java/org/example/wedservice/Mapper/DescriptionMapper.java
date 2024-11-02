package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.DescriptionRequest;
import org.example.wedservice.Dto.Response.DescriptionResponse;
import org.example.wedservice.Entity.Description;
import org.example.wedservice.Form.Description_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DescriptionMapper {
    @Mapping(source = "iddescription",target = "id")
    DescriptionResponse toDescriptionResponse(Description description);
    Description toDescription(DescriptionRequest request);
    void updateDescription(@MappingTarget Description request, Description_Update update);
}
