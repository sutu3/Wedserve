package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.PurchaseRequest;
import org.example.wedservice.Dto.Request.Purchase_ItemRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.Purchase_ItemResponse;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Form.Purchase_Item_Update;
import org.example.wedservice.Form.Purchase_Update;
import org.example.wedservice.Service.Purchase_ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase_items")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class Purchase_ItemController {
    Purchase_ItemService purchaseItemService;
    @GetMapping
    public ApiResponse<List<Purchase_ItemResponse>> getall(){
        return ApiResponse.<List<Purchase_ItemResponse>>builder()
                .Result(purchaseItemService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<Purchase_ItemResponse> getById(@PathVariable String id) {
        return ApiResponse.<Purchase_ItemResponse>builder()
                .Result(purchaseItemService.Getbyid(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping()
    public ApiResponse<Purchase_ItemResponse> postMaterial(@RequestBody Purchase_ItemRequest request){
        return ApiResponse.<Purchase_ItemResponse>builder()
                .Result(purchaseItemService.PostPurchase_item(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<Purchase_ItemResponse> putPurchase(@PathVariable String id, @RequestBody Purchase_Item_Update update)
           {
        return ApiResponse.<Purchase_ItemResponse>builder()
                .Result(purchaseItemService.putPurchase_Item(id,update))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCategory(@PathVariable String id){
        return ApiResponse.<String>builder()
                .Result(purchaseItemService.DeletePurchase_Item(id))
                .message("Delete Completed")
                .code(0)
                .success(true)
                .build();
    }
}
