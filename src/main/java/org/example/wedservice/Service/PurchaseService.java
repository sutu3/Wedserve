package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.PurchaseRequest;
import org.example.wedservice.Dto.Response.PurchaseResponse;
import org.example.wedservice.Entity.Purchase;
import org.example.wedservice.Entity.Purchase_Item;
import org.example.wedservice.Enum.StatusPurchase;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Purchase_Update;
import org.example.wedservice.Mapper.PurchaseMapper;
import org.example.wedservice.Repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class PurchaseService {
    PurchaseRepository purchaseRepository;
    PurchaseMapper purchaseMapper;

    public List<PurchaseResponse> getall(){
        return purchaseRepository.findAll().stream()
                .map(purchaseMapper::toPurchaseResponse)
                .collect(Collectors.toList());
    }
    public PurchaseResponse Getbyid(String id) {
        return purchaseMapper.toPurchaseResponse(purchaseRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.PURCHASE_NOT_FOUND)));
    }
    public PurchaseResponse PostPurchase(PurchaseRequest request) {
        Purchase purchase= purchaseMapper.toPurchase(request);
        log.info(LocalDateTime.now().toString());
        return purchaseMapper.toPurchaseResponse(purchaseRepository.save(
                purchase.builder()
                        .status(StatusPurchase.Created)
                        .totalamoung(purchase.getTotalamoung())
                        .createat(LocalDateTime.now())
                        .build()));
    }
    public List<Purchase_Item> getallList(String id) {
        if(!purchaseRepository.existsById(id)){
            throw new AppException(ErrorCode.PURCHASE_NOT_FOUND);
        }
        return purchaseRepository.findById(id).get().getItems();
    }
    public PurchaseResponse putPurchase(String id, Purchase_Update update) {
        Purchase purchase= purchaseRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.PURCHASE_NOT_FOUND));
        purchase.setStatus(StatusPurchase.Pending);
        purchase.setTotalamoung(BigDecimal.valueOf(update.getTotalamoung()));
        purchaseMapper.updateMaterial(purchase,update);
        purchase.setUpdateat(LocalDateTime.now());
        return purchaseMapper.toPurchaseResponse(purchaseRepository.save(purchase));
    }
    public PurchaseResponse importPurchase(String id) {
        Purchase purchase= purchaseRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.PURCHASE_NOT_FOUND));
        purchase.setStatus(StatusPurchase.Completed);
        purchase.setUpdateat(LocalDateTime.now());
        return purchaseMapper.toPurchaseResponse(purchaseRepository.save(purchase));
    }

}
