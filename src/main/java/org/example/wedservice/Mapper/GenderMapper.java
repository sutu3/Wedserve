package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.GenderRequest;
import org.example.wedservice.Dto.Response.GenderResponse;
import org.example.wedservice.Entity.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenderMapper {
    GenderResponse toGenderResponse(Gender gender);
    Gender togender(GenderRequest request);
}
