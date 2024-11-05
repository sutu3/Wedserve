package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.ProductRequest;
import org.example.wedservice.Dto.Request.PurchaseRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.ProductResponse;
import org.example.wedservice.Dto.Response.PurchaseResponse;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Form.Product_Update;
import org.example.wedservice.Form.Purchase_Update;
import org.example.wedservice.Service.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PurchaseController {
    PurchaseService purchaseService;
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
    public ApiResponse<PurchaseResponse> getById(@PathVariable String id) throws AppException {
        return ApiResponse.<PurchaseResponse>builder()
                .Result(purchaseService.Getbyid(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping()
    public ApiResponse<PurchaseResponse> postMaterial(@RequestBody PurchaseRequest request) throws AppException {
        return ApiResponse.<PurchaseResponse>builder()
                .Result(purchaseService.PostPurchase(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<PurchaseResponse> putPurchase(@PathVariable String id, @RequestBody Purchase_Update update)
            throws AppException {
        return ApiResponse.<PurchaseResponse>builder()
                .Result(purchaseService.putPurchase(id,update))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }

}
