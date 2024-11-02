package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Dto.Request.MaterialRequest;
import org.example.wedservice.Dto.Request.ProductRequest;
import org.example.wedservice.Dto.Response.MaterialResponse;
import org.example.wedservice.Dto.Response.ProductResponse;
import org.example.wedservice.Entity.Material;
import org.example.wedservice.Entity.Product;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Material_Update;
import org.example.wedservice.Form.Product_Update;
import org.example.wedservice.Mapper.MaterialMapper;
import org.example.wedservice.Mapper.ProductMapper;
import org.example.wedservice.Repository.MaterialRepository;
import org.example.wedservice.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;
    MaterialRepository materialRepository;
    public List<ProductResponse> getall(){
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
    public ProductResponse Getbyid(String id) throws AppException {
        return productMapper.toProductResponse(productRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.PRODUCT_NOT_FOUND)));
    }
    public ProductResponse PostProduct(ProductRequest request) throws AppException {
        if(!materialRepository.existsByName(request.getMaterialName()))
        {
            throw new AppException(ErrorCode.MATERIAL_NOT_FOUND);
        }
        Product product= productMapper.toProduct(request);
        product.builder()
                .createat(LocalDate.now())
                .isdeleted(false)
                .build();
        product.setMaterials(materialRepository.findFistByName(request.getMaterialName()));
        if(productRepository.existsByName(product.getName())){
            throw new AppException(ErrorCode.PRODUCT_IS_EXITED);
        }
        product.builder()
                .isdeleted(false)
                .createat(LocalDate.now())
                .build();
        return productMapper.toProductResponse(productRepository.save(product));
    }
    public ProductResponse PutProduct(String id, Product_Update update) throws AppException {
        Product product= productRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productMapper.updateUpdate(product,update);
        product.setUpdateat(LocalDate.now());
        return productMapper.toProductResponse(productRepository.save(product));
    }
    public void DeleteProduct(String id) throws AppException {
        Product product= productRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        product.builder()
                .deleteat(LocalDate.now())
                .isdeleted(true)
                .build();
    }
}
