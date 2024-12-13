package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.InventoryRequest;
import org.example.wedservice.Dto.Request.OrderItemQuanlityResquest;
import org.example.wedservice.Dto.Request.OrderItemResquest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.InventoryResponse;
import org.example.wedservice.Dto.Response.OrderItemResponse;
import org.example.wedservice.Form.OrderItem_Update;
import org.example.wedservice.Service.InventoryService;
import org.example.wedservice.Service.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class InventoryController {
    InventoryService inventoryService;
    @GetMapping
    public ApiResponse<List<InventoryResponse>> getall(){
        return ApiResponse.<List<InventoryResponse>>builder()
                .Result(inventoryService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    /*@GetMapping("/{id}")
    public ApiResponse<OrderItemResponse> getById(@PathVariable String id) {
        return ApiResponse.<OrderItemResponse>builder()
                .Result(orderItemService.getbyid(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }*/
    @PostMapping()
    public ApiResponse<InventoryResponse> PostInventory(@RequestBody InventoryRequest request){
        return ApiResponse.<InventoryResponse>builder()
                .Result(inventoryService.PostInventory(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }

    @PutMapping("/sale/{id}")
    public ApiResponse<InventoryResponse> PutOrderItemQuanlity(@PathVariable String id)
    {
        log.info("hehehehehehe");
        return ApiResponse.<InventoryResponse>builder()
                .Result(inventoryService.SaleStatus(id))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> DeleteInventory(@PathVariable String id) {
        return ApiResponse.<String>builder()
               .message("Delete Completed")
                .Result(inventoryService.DeleteInventory(id))
               .code(0)
               .success(true)
               .build();
    }

}
