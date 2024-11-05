package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.MaterialRequest;
import org.example.wedservice.Dto.Request.PurchaseRequest;
import org.example.wedservice.Dto.Response.MaterialResponse;
import org.example.wedservice.Dto.Response.PurchaseResponse;
import org.example.wedservice.Entity.Material;
import org.example.wedservice.Entity.Purchase;
import org.example.wedservice.Entity.Purchase_Item;
import org.example.wedservice.Enum.StatusPurchase;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Material_Update;
import org.example.wedservice.Form.Purchase_Update;
import org.example.wedservice.Mapper.PurchaseMapper;
import org.example.wedservice.Repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
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
    public PurchaseResponse Getbyid(String id) throws AppException {
        return purchaseMapper.toPurchaseResponse(purchaseRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.PURCHASE_NOT_FOUND)));
    }
    public PurchaseResponse PostPurchase(PurchaseRequest request) throws AppException {
        Purchase purchase= purchaseMapper.toPurchase(request);
        log.info(LocalDateTime.now().toString());
        ;
        return purchaseMapper.toPurchaseResponse(purchaseRepository.save(
                purchase.builder()
                        .status(StatusPurchase.Created)
                        .alamoung(purchase.getAlamoung())
                        .createat(LocalDateTime.now())
                        .build()));
    }
    public List<Purchase_Item> getallList(String id) throws AppException {
        if(!purchaseRepository.existsById(id)){
            throw new AppException(ErrorCode.PURCHASE_NOT_FOUND);
        }
        return purchaseRepository.findById(id).get().getItems();
    }
    public PurchaseResponse putPurchase(String id, Purchase_Update update) throws AppException {
        Purchase purchase= purchaseRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.PURCHASE_NOT_FOUND));
        purchaseMapper.updateMaterial(purchase,update);
        purchase.setUpdateat(LocalDateTime.now());
        return purchaseMapper.toPurchaseResponse(purchaseRepository.save(purchase));
    }

}
