package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.OrderItemResquest;
import org.example.wedservice.Dto.Response.OrderItemResponse;
import org.example.wedservice.Entity.Order_Item;
import org.example.wedservice.Form.OrderItem_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "order",ignore = true)
    Order_Item toOrderItem(OrderItemResquest request);// Ánh xạ idMaterial của entity với id của DTO
    OrderItemResponse tOrderItemResponse(Order_Item orderitem);
    @Mapping(target = "order",ignore = true)
    void updateOrder_Item(@MappingTarget Order_Item item, OrderItem_Update update);
}
