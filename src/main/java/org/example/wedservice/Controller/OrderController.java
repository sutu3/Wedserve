package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.OrderResquest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.OrderResponse;
import org.example.wedservice.Dto.Response.OrderWithUserResponse;
import org.example.wedservice.Form.Order_Update;
import org.example.wedservice.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderController {
    OrderService orderService;
    @GetMapping
    public ApiResponse<List<OrderWithUserResponse>> getall(){
        return ApiResponse.<List<OrderWithUserResponse>>builder()
                .Result(orderService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> getById(@PathVariable String id) {
        return ApiResponse.<OrderResponse>builder()
                .Result(orderService.getbyid(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping()
    public ApiResponse<OrderResponse> PostOrder(@RequestBody OrderResquest request){
        return ApiResponse.<OrderResponse>builder()
                .Result(orderService.postOrder(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<OrderResponse> PutOrder(@PathVariable String id, @RequestBody Order_Update update)
            {
        return ApiResponse.<OrderResponse>builder()
                .Result(orderService.changeStatus(id,update))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }

    @PutMapping("/delivered/{id}")
    public ApiResponse<OrderResponse> PutOrderDeliver(@PathVariable String id)
    {
        return ApiResponse.<OrderResponse>builder()
                .Result(orderService.changeStatusDelivered(id))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @PutMapping("/shipping/{id}")
    public ApiResponse<OrderWithUserResponse> PutOrderShipping(@PathVariable String id)
    {
        return ApiResponse.<OrderWithUserResponse>builder()
                .Result(orderService.changeStatusShipping(id))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
   /* @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable String id) {
        orderService.DeleteCategory(id);
        return ApiResponse.<Void>builder()
               .message("Delete Completed")
               .code(0)
               .success(true)
               .build();
    }*/
}
