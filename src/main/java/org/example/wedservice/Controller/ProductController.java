package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.DescriptionRequest;
import org.example.wedservice.Dto.Request.ProductRequest;
import org.example.wedservice.Dto.Request.ProductWithImageRequest;
import org.example.wedservice.Dto.Request.VarientRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.DescriptionResponse;
import org.example.wedservice.Dto.Response.ProductResponse;
import org.example.wedservice.Dto.Response.VarientResponse;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Product_Update;
import org.example.wedservice.Service.DescriptionService;
import org.example.wedservice.Service.ProductService;
import org.example.wedservice.Service.VarientService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductController {
    ProductService productService;
    VarientService varientService;
    private final DescriptionService descriptionService;

    @GetMapping
    public ApiResponse<List<ProductResponse>> getall(){
        return ApiResponse.<List<ProductResponse>>builder()
                .Result(productService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getById(@PathVariable String id) {
        return ApiResponse.<ProductResponse>builder()
                .Result(productService.Getbyid(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping()
    public ApiResponse<ProductResponse> postProduct(@RequestBody ProductRequest request){
        return ApiResponse.<ProductResponse>builder()
                .Result(productService.PostProduct(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @PostMapping("/descriptions")
    public ApiResponse<ProductResponse> postDescriptions(@RequestBody List<DescriptionRequest> descriptions) {
        if (descriptions.isEmpty()) {
            throw new AppException(ErrorCode.DESCRIPTIONS_EMPTY);
        }
        List<DescriptionResponse> descriptionResponses = descriptions.stream()
                .map(descriptionService::PostDescription)
                .toList();
        ProductResponse productResponse = productService.PutProductDescription(
                descriptions.get(0).getIdproduct(),
                descriptionResponses
        );
        return ApiResponse.<ProductResponse>builder()
                .Result(productResponse)
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping("/variants")
    public ApiResponse<ProductResponse> createVariants(@RequestBody List<VarientRequest> variants) {
        if (variants.isEmpty()) {
            throw new AppException(ErrorCode.VARIANTS_EMPTY);
        }

        List<VarientResponse> varientResponses = variants.stream()
                .map(varientService::PostVarient)
                .toList();

        ProductResponse productResponse = productService.addVarientToProduct(
                variants.get(0).getIdproduct(),
                varientResponses
        );

        return ApiResponse.<ProductResponse>builder()
                .Result(productResponse)
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PutMapping("/image")
    public ApiResponse<ProductResponse> putProductWithImage(@RequestBody ProductWithImageRequest request)
    {
        return ApiResponse.<ProductResponse>builder()
                .Result(productService.PutProductWithImage(request))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> putProduct(@PathVariable String id, @RequestBody Product_Update update)
           {
        return ApiResponse.<ProductResponse>builder()
                .Result(productService.PutProduct(id,update))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable String id) {
        productService.DeleteProduct(id);
        return ApiResponse.<Void>builder()
               .message("Delete Completed")
               .code(0)
               .success(true)
               .build();
    }
}
