package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Dto.Request.OrderResquest;
import org.example.wedservice.Dto.Response.OrderResponse;
import org.example.wedservice.Dto.Response.OrderWithUserResponse;
import org.example.wedservice.Entity.Orders;
import org.example.wedservice.Entity.Shipping;
import org.example.wedservice.Enum.OrderStatus;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Order_Update;
import org.example.wedservice.Mapper.OrderMapper;
import org.example.wedservice.Repository.OrderRepository;
import org.example.wedservice.Repository.ShippingRepository;
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
    private final ShippingRepository shippingRepository;

    public List<OrderWithUserResponse> getall(){
        return orderRepository.findAll().stream().map(orderMapper::tOrderWithUserResponse)
                .collect(Collectors.toList());
    }
   public OrderResponse getbyid(String id) {
        return orderMapper.tOrderResponse(orderRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.ORDER_NOT_FOUND)));
    }
    public OrderResponse changeStatus(String id, Order_Update resquest){
        Orders order=orderRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.ORDER_NOT_FOUND));
        order.setStatus(OrderStatus.CONFIRMED.name());
        order.setTotalamount(resquest.getTotalamoung());
        order.setOrderdate(LocalDateTime.now());
        order.setUpdatedat(LocalDateTime.now());
        Orders orderUpdate=orderRepository.save(order);
        Shipping shipping=new Shipping();
        shipping.setOrder(orderUpdate);
        shipping.setStatus(OrderStatus.CONFIRMED.name());
        shipping.setCreatedat(LocalDateTime.now());
        shippingRepository.save(shipping);

        return orderMapper.tOrderResponse(orderUpdate);
    }
    public OrderWithUserResponse changeStatusShipping(String id){
        Orders order=orderRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.ORDER_NOT_FOUND));
        order.setStatus(OrderStatus.SHIPPED.name());
        Shipping shipping=new Shipping();
        shipping.setOrder(order);
        shipping.setStatus(OrderStatus.SHIPPED.name());
        shipping.setCreatedat(LocalDateTime.now());
        shippingRepository.save(shipping);

        return orderMapper.tOrderWithUserResponse(order);
    }
    public OrderResponse changeStatusDelivered(String id){
        Orders order=orderRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.ORDER_NOT_FOUND));
        order.setStatus(OrderStatus.DELIVERED.name());
        Shipping shipping=new Shipping();
        shipping.setOrder(order);
        shipping.setStatus(OrderStatus.DELIVERED.name());
        shipping.setCreatedat(LocalDateTime.now());
        shippingRepository.save(shipping);

        return orderMapper.tOrderResponse(order);
    }
    public OrderResponse postOrder(OrderResquest request){
        return orderMapper.tOrderResponse(orderRepository.save(Orders.builder()
                .createdat(LocalDateTime.now())
                        .totalamount(request.getTotalamount())
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
