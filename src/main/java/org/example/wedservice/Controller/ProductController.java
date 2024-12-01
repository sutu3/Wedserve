package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.ProductRequest;
import org.example.wedservice.Dto.Request.ProductWithImageRequest;
import org.example.wedservice.Dto.Request.VarientRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.ProductResponse;
import org.example.wedservice.Dto.Response.VarientResponse;
import org.example.wedservice.Form.Product_Update;
import org.example.wedservice.Service.ProductService;
import org.example.wedservice.Service.VarientService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductController {
    ProductService productService;
    VarientService varientService;

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
    @PostMapping("/variants")
    public ApiResponse<ProductResponse> createVariants(@RequestBody List<VarientRequest> variants) {
        List<String> result = new ArrayList<>();

        for (VarientRequest variant : variants) {
            VarientResponse varientResponse=varientService.PostVarient(variant);
            productService.addVarientToProduct(variant.getIdproduct(), varientResponse);
        }
        return ApiResponse.<ProductResponse>builder()
                .Result(productService.Getbyid(variants.get(0).getIdproduct()))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PutMapping
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
