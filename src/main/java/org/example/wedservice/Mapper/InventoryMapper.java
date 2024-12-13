package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.InventoryRequest;
import org.example.wedservice.Dto.Request.Purchase_ItemRequest;
import org.example.wedservice.Dto.Response.InventoryResponse;
import org.example.wedservice.Dto.Response.Purchase_ItemResponse;
import org.example.wedservice.Entity.Inventory;
import org.example.wedservice.Entity.Purchase_Item;
import org.example.wedservice.Form.Purchase_Item_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    @Mapping( target = "orderitem",ignore = true)
    Inventory toInventory(InventoryRequest request);
    InventoryResponse toInventoryResponse(Inventory inventory);
}
