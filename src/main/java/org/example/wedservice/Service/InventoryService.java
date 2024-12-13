package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.InventoryRequest;
import org.example.wedservice.Dto.Response.InventoryResponse;
import org.example.wedservice.Entity.Inventory;
import org.example.wedservice.Entity.Order_Item;
import org.example.wedservice.Entity.Varient;
import org.example.wedservice.Entity.Version;
import org.example.wedservice.Enum.Even_Type;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Mapper.InventoryMapper;
import org.example.wedservice.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class InventoryService {
    InventoryRepository inventoryRepository;
    InventoryMapper inventoryMapper;
    OrderItemRepository orderItemRepository;
    VarientRepository varientRepository;
    VersionRepository versionRepository;

    public List<InventoryResponse> getall(){
        return inventoryRepository.findAll().stream().map(inventoryMapper::toInventoryResponse)
                .collect(Collectors.toList());
    }
    public InventoryResponse PostInventory(InventoryRequest request){
        Inventory inventory=inventoryMapper.toInventory(request);
        log.info("hdhdhhdhd");
        Varient varient=varientRepository.findFirstByProduct_NameAndColor_colornameAndSize_Sizename(request.getProductname(), request.getColorname(), request.getSizename());
        Version version= versionRepository.findFirstByIsdeletedFalseAndVarient_Idvariant(varient.getIdvariant());
        log.info(version.getIdversion().toString());
        Order_Item item=orderItemRepository.findById(request.getOrderitem())
                .orElseThrow(()->new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND));
        log.info(item.getIdorderitem().toString());
        version.setQuantity_in_stock(version.getQuantity_in_stock()-request.getChange_amount());
        versionRepository.save(version);
        inventory.setCreateat(LocalDateTime.now());
        inventory.setOrderitem(item);
        inventory.setVersion(version);
        Inventory inventory1=inventoryRepository.save(inventory);
        InventoryResponse inventoryResponse= inventoryMapper.toInventoryResponse(inventory1);
        item.setInventory(inventory1);
        orderItemRepository.save(item);
        return inventoryResponse;
    }
    public InventoryResponse SaleStatus(String idinventory){
        Inventory inventory=inventoryRepository.findById(idinventory).
                orElseThrow(()->new AppException(ErrorCode.INVENTORY_NOT_FOUND));
        inventory.setEvent(Even_Type.SALE.name());
        return inventoryMapper.toInventoryResponse(inventoryRepository.save(inventory));
    }
    public String DeleteInventory(String id) {
        inventoryRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.INVENTORY_NOT_FOUND));
        inventoryRepository.deleteById(id);
        return id;
    }
}
