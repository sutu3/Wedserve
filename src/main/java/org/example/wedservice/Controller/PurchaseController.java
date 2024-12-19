package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.DescriptionRequest;
import org.example.wedservice.Dto.Request.ProductRequest;
import org.example.wedservice.Dto.Request.PurchaseRequest;
import org.example.wedservice.Dto.Request.Purchase_ItemRequest;
import org.example.wedservice.Dto.Response.*;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Inventory_Update;
import org.example.wedservice.Form.Product_Update;
import org.example.wedservice.Form.Purchase_Update;
import org.example.wedservice.Service.InventoryService;
import org.example.wedservice.Service.PurchaseService;
import org.example.wedservice.Service.Purchase_ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PurchaseController {
    PurchaseService purchaseService;
    Purchase_ItemService purchaseItemService;
    private final InventoryService inventoryService;

    @GetMapping
    public ApiResponse<List<PurchaseResponse>> getall(){
        return ApiResponse.<List<PurchaseResponse>>builder()
                .Result(purchaseService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<PurchaseResponse> getById(@PathVariable String id){
        return ApiResponse.<PurchaseResponse>builder()
                .Result(purchaseService.Getbyid(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping("/purchase_items")
    public ApiResponse<PurchaseResponse> postDescriptions(@RequestBody List<Purchase_ItemRequest> purchase_itemRequests) {
        if (purchase_itemRequests.isEmpty()) {
            throw new AppException(ErrorCode.PURCHASE_ITEMS_EMPTY);
        }
        List<Purchase_ItemResponse> purchaseItemResponses = purchase_itemRequests.stream()
                .map(purchaseItemService::PostPurchase_item)
                .toList();

        return ApiResponse.<PurchaseResponse>builder()
                .Result(purchaseService.Getbyid(purchaseItemResponses.get(0).getPurchase().getId()))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }@PutMapping("/changestate/{id}")
    public ApiResponse<PurchaseResponse> changeStatePurchase(@PathVariable String id,@RequestBody List<Inventory_Update> inventoryImport) {
        if (inventoryImport.isEmpty()) {
            throw new AppException(ErrorCode.INVENTORYS_EMPTY);
        }
        List<InventoryResponse> InventoryResponses = inventoryImport.stream()
                .map(inventoryService::PostImportInventory)
                .toList();

        return ApiResponse.<PurchaseResponse>builder()
                .Result(purchaseService.importPurchase(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }

    @PostMapping()
    public ApiResponse<PurchaseResponse> postMaterial(@RequestBody PurchaseRequest request){
        return ApiResponse.<PurchaseResponse>builder()
                .Result(purchaseService.PostPurchase(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<PurchaseResponse> putPurchase(@PathVariable String id, @RequestBody Purchase_Update update)
           {
        return ApiResponse.<PurchaseResponse>builder()
                .Result(purchaseService.putPurchase(id,update))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }

}
