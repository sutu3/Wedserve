package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.OrderResquest;
import org.example.wedservice.Dto.Request.ShippingRequest;
import org.example.wedservice.Dto.Response.OrderResponse;
import org.example.wedservice.Dto.Response.OrderWithUserResponse;
import org.example.wedservice.Dto.Response.ShippingResponse;
import org.example.wedservice.Entity.Orders;
import org.example.wedservice.Entity.Purchase_Item;
import org.example.wedservice.Entity.Shipping;
import org.example.wedservice.Form.Purchase_Item_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShippingMapper {
    @Mapping(target = "order",ignore = true)
    Shipping toShipping(ShippingRequest request);// Ánh xạ idMaterial của entity với id của DTO
    ShippingResponse tShippingResponse(Shipping shipping);
    void updatePurchase_Item(@MappingTarget Purchase_Item item, Purchase_Item_Update update);
}
