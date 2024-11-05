package org.example.wedservice.Mapper;

import org.example.wedservice.Dto.Request.PurchaseRequest;
import org.example.wedservice.Dto.Request.Purchase_ItemRequest;
import org.example.wedservice.Dto.Response.PurchaseResponse;
import org.example.wedservice.Dto.Response.Purchase_ItemResponse;
import org.example.wedservice.Entity.Purchase;
import org.example.wedservice.Entity.Purchase_Item;
import org.example.wedservice.Form.Purchase_Item_Update;
import org.example.wedservice.Form.Purchase_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface Purchase_itemMapper {
    Purchase_Item toPurchaseItem(Purchase_ItemRequest request);
    @Mapping(source = "idpurchaseitem", target = "id") // Ánh xạ idMaterial của entity với id của DTO
    Purchase_ItemResponse toPurchase_ItemResponse(Purchase_Item purchase_item);
    void updatePurchase_Item(@MappingTarget Purchase_Item item, Purchase_Item_Update update);
}
