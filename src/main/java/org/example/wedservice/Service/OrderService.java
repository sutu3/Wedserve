package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Dto.Request.OrderResquest;
import org.example.wedservice.Dto.Response.OrderResponse;
import org.example.wedservice.Entity.Orders;
import org.example.wedservice.Enum.OrderStatus;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Mapper.OrderMapper;
import org.example.wedservice.Repository.OrderRepository;
import org.example.wedservice.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    private final UserRepository userRepository;

    public List<OrderResponse> getall(){
        return orderRepository.findAll().stream().map(orderMapper::tOrderResponse)
                .collect(Collectors.toList());
    }
   public OrderResponse getbyid(String id) {
        return orderMapper.tOrderResponse(orderRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.ORDER_NOT_FOUND)));
    }

     public OrderResponse postOrder(OrderResquest request){
        return orderMapper.tOrderResponse(orderRepository.save(Orders.builder()
                .createdat(LocalDateTime.now())
                .user(userRepository.findById(request.getUserid())
                        .orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND)))
                .status(OrderStatus.PENDING.toString())
                .totalamount(BigDecimal.valueOf(0))
                .isDeleted(false)
                .build()));
    }
   /* public CategoryResponse putCategory(String id, Category_Update update)  {
        Category category= orderRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        orderMapper.updateCategory(category,update);
        category.setUpdateat(LocalDateTime.now());
        return orderMapper.toCategoryResponse(orderRepository.save(category)) ;
    }
    public void DeleteCategory(String id) {
        Category category= orderRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        category.setIsdeleted(true);
        category.setDeleteat(LocalDateTime.now());
       orderRepository.save(category);
    }*/
}
