package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.PurchaseRequest;
import org.example.wedservice.Dto.Response.PurchaseResponse;
import org.example.wedservice.Entity.Purchase;
import org.example.wedservice.Form.Purchase_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    Purchase toPurchase(PurchaseRequest request);
    @Mapping(source = "idpurchase", target = "id") // Ánh xạ idMaterial của entity với id của DTO
    PurchaseResponse toPurchaseResponse(Purchase purchase);
    void updateMaterial(@MappingTarget Purchase purchase, Purchase_Update update);
}
