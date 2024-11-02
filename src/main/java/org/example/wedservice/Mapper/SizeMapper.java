package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.SizeRequest;
import org.example.wedservice.Dto.Response.SizeResponse;
import org.example.wedservice.Entity.Size;
import org.example.wedservice.Form.Size_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SizeMapper {

    Size toSize(SizeRequest request);
    @Mapping(source = "idsize",target = "id")
    SizeResponse toSizeResponse(Size size);
    void UpdataSizename(@MappingTarget Size size, Size_Update update);
}
