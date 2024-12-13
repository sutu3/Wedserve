package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.OrderItemQuanlityResquest;
import org.example.wedservice.Dto.Request.OrderItemResquest;
import org.example.wedservice.Dto.Response.OrderItemResponse;
import org.example.wedservice.Entity.Order_Item;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.OrderItem_Update;
import org.example.wedservice.Mapper.OrderItemMapper;
import org.example.wedservice.Repository.OrderItemRepository;
import org.example.wedservice.Repository.OrderRepository;
import org.example.wedservice.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class OrderItemService {
    OrderItemRepository orderItemRepository;
    OrderItemMapper orderItemMapper;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public List<OrderItemResponse> getall(){
        return orderItemRepository.findAll().stream().map(orderItemMapper::tOrderItemResponse)
                .collect(Collectors.toList());
    }
   public OrderItemResponse getbyid(String id) {
        return orderItemMapper.tOrderItemResponse(orderItemRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND)));
    }

    public OrderItemResponse changeQuanlityItem(String id,OrderItemQuanlityResquest resquest){
        log.info("heheheheh");
        Order_Item item= orderItemRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND));
        item.setQuantity(resquest.getQuantity());
        item.setUpdatedat(LocalDateTime.now());
        return orderItemMapper.tOrderItemResponse(orderItemRepository.save(item));
    }
     public OrderItemResponse postOrderItem(OrderItemResquest request){
        Order_Item item=orderItemMapper.toOrderItem(request);
        item.setOrder(orderRepository.getById(request.getOrderid()));
        item.setCreatedat(LocalDateTime.now());
        return orderItemMapper.tOrderItemResponse(orderItemRepository.save(item));
    }
    public OrderItemResponse putOrderItem(String id, OrderItem_Update update)  {
        Order_Item item= orderItemRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND));
        orderItemMapper.updateOrder_Item(item, update);
        item.setUpdatedat(LocalDateTime.now());
        return orderItemMapper.tOrderItemResponse(orderItemRepository.save(item)) ;
    }
    public String DeleteOrderItem(String id) {
       orderItemRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND));
    orderItemRepository.deleteById(id);
    return id;
    }
}
