package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.OrderItemResquest;
import org.example.wedservice.Dto.Request.OrderResquest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.OrderItemResponse;
import org.example.wedservice.Dto.Response.OrderResponse;
import org.example.wedservice.Form.OrderItem_Update;
import org.example.wedservice.Service.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderItemController {
    OrderItemService orderItemService;
    @GetMapping
    public ApiResponse<List<OrderItemResponse>> getall(){
        return ApiResponse.<List<OrderItemResponse>>builder()
                .Result(orderItemService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<OrderItemResponse> getById(@PathVariable String id) {
        return ApiResponse.<OrderItemResponse>builder()
                .Result(orderItemService.getbyid(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping()
    public ApiResponse<OrderItemResponse> PostOrderItem(@RequestBody OrderItemResquest request){
        return ApiResponse.<OrderItemResponse>builder()
                .Result(orderItemService.postOrderItem(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<OrderItemResponse> PutCategory(@PathVariable String id, @RequestBody OrderItem_Update update)
            {
        return ApiResponse.<OrderItemResponse>builder()
                .Result(orderItemService.putOrderItem(id,update))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCategory(@PathVariable String id) {
        return ApiResponse.<String>builder()
               .message("Delete Completed")
                .Result(orderItemService.DeleteOrderItem(id))
               .code(0)
               .success(true)
               .build();
    }
}
