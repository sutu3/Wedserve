package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.Purchase_ItemRequest;
import org.example.wedservice.Dto.Response.Purchase_ItemResponse;
import org.example.wedservice.Entity.Purchase;
import org.example.wedservice.Entity.Purchase_Item;
import org.example.wedservice.Entity.Version;
import org.example.wedservice.Enum.StatusPurchase;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Purchase_Item_Update;
import org.example.wedservice.Mapper.PurchaseMapper;
import org.example.wedservice.Mapper.Purchase_itemMapper;
import org.example.wedservice.Repository.PurchaseRepository;
import org.example.wedservice.Repository.Purchase_ItemRepository;
import org.example.wedservice.Repository.VersionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class Purchase_ItemService {
    Purchase_ItemRepository purchaseItemRepository;
    Purchase_itemMapper purchaseItemMapper;
    PurchaseRepository purchaseRepository;
    PurchaseMapper purchaseMapper;
    PurchaseService purchaseService;
    VersionRepository versionRepository;

    public List<Purchase_ItemResponse> getall() {
        return purchaseItemRepository.findAll().stream()
                .map(purchaseItemMapper::toPurchase_ItemResponse)
                .collect(Collectors.toList());
    }

    public Purchase_ItemResponse Getbyid(String id) {
        return purchaseItemMapper.toPurchase_ItemResponse(purchaseItemRepository
                .findById(id).
                orElseThrow(() -> new AppException(ErrorCode.PURCHASE_NOT_FOUND)));
    }

    public Purchase_ItemResponse PostPurchase_item(Purchase_ItemRequest request) {
        Purchase_Item purchaseItem = purchaseItemMapper.toPurchaseItem(request);
        Purchase purchase = purchaseRepository.findById(
                request.getIdpurchase()).orElseThrow(() -> new AppException
                (ErrorCode.PURCHASE_NOT_FOUND));
        BigDecimal amount = purchase.getAlamoung();
        Version version = versionRepository.findById(request.getIdVersion())
                .orElseThrow(() -> new AppException(ErrorCode.VERSION_NOT_FOUND));
        BigDecimal quantity = new BigDecimal(request.getQuantity());
        BigDecimal totalAmount = quantity.multiply(version.getOriginalprice());
        BigDecimal updatedAmount = amount.add(totalAmount);
        purchase.setAlamoung(updatedAmount);
        purchaseRepository.save(purchase);
        Purchase_Item purchase_item = purchaseItemRepository.save(purchaseItem.builder()
                .purchase(purchase)
                .purchaseprice(request.getPurchaseprice())
                .createat(LocalDateTime.now())
                .quantity(request.getQuantity())
                .totalprice(version.getOriginalprice())
                .version(version)
                .build());
        log.info(request.getPurchaseprice().getClass().getSimpleName() + request.getPurchaseprice());
        List<Purchase_Item> items = (purchaseRepository.findById(request.getIdpurchase())
                .get().getItems());
        List<Purchase_Item> combinedItems = new ArrayList<>(items); // Use HashSet to avoid duplicates
        combinedItems.add(purchase_item);
        purchaseMapper.toPurchaseResponseNoListItem(
                purchaseRepository.save(purchase.builder()
                        .idpurchase(request.getIdpurchase())
                        .items(combinedItems)
                        .status(StatusPurchase.Created)
                        .createat(purchase.getCreateat())
                        .updateat(LocalDateTime.now())
                        .build()));
        Purchase_ItemResponse itemresponse = purchaseItemMapper
                .toPurchase_ItemResponse(purchase_item);
        return itemresponse;
    }

    public Purchase_ItemResponse putPurchase_Item(String id, Purchase_Item_Update update) {
        Purchase_Item purchase_item = purchaseItemRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.PURCHASE_ITEM_NOT_FOUND));
        purchaseItemMapper.updatePurchase_Item(purchase_item, update);
        purchase_item.setUpdateat(LocalDateTime.now());
        return purchaseItemMapper.toPurchase_ItemResponse(
                purchaseItemRepository.save(purchase_item));
    }

    public void DeletePurchase_Item(String id) {
        if (!purchaseRepository.existsById(id)) {
            throw new AppException(ErrorCode.PURCHASE_NOT_FOUND);
        }
        purchaseItemRepository.deleteById(id);
    }
}
