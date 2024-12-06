package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Dto.Request.MaterialRequest;
import org.example.wedservice.Dto.Request.ProductRequest;
import org.example.wedservice.Dto.Request.ProductWithImageRequest;
import org.example.wedservice.Dto.Response.DescriptionResponse;
import org.example.wedservice.Dto.Response.MaterialResponse;
import org.example.wedservice.Dto.Response.ProductResponse;
import org.example.wedservice.Dto.Response.VarientResponse;
import org.example.wedservice.Entity.*;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Material_Update;
import org.example.wedservice.Form.Product_Update;
import org.example.wedservice.Mapper.DescriptionMapper;
import org.example.wedservice.Mapper.MaterialMapper;
import org.example.wedservice.Mapper.ProductMapper;
import org.example.wedservice.Mapper.VarientMapper;
import org.example.wedservice.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;
    MaterialRepository materialRepository;
    UploadImageFileService uploadFile;
    ImageRepository imageRepository;
    VarientRepository varientRepository;
    VarientMapper varientMapper;
    CategoryRepository categoryRepository;
    GenderRepository genderRepository;
    DescriptionMapper descriptionMapper;
    public List<ProductResponse> getall(){
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
    public ProductResponse Getbyid(String id){
        return productMapper.toProductResponse(productRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.PRODUCT_NOT_FOUND)));
    }
    public ProductResponse PostProduct(ProductRequest request){
        if(!materialRepository.existsByName(request.getMaterialName()))
        {
            throw new AppException(ErrorCode.MATERIAL_NOT_FOUND);
        }
        Product product= productMapper.toProduct(request);
        if(!categoryRepository.existsByName(request.getCategoryname()))
        {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        if(!materialRepository.existsByName(request.getMaterialName()))
        {
            throw new AppException(ErrorCode.MATERIAL_NOT_FOUND);
        }
        Category category=categoryRepository.findByName(request.getCategoryname());
        if(!genderRepository.existsByGender(request.getGender()))
        {
            throw new AppException(ErrorCode.GENDER_INVALID);
        }
        Gender gender= genderRepository.findByGender(request.getGender());
        product.setMaterials(materialRepository.findByName(request.getMaterialName()));
        product.setCreateat(LocalDateTime.now());
        product.setCategory(category);
        product.setGender(gender);
        product.setDescriptions(null);
        if(productRepository.existsByName(product.getName())){
            throw new AppException(ErrorCode.PRODUCT_IS_EXITED);
        }
        return productMapper.toProductResponse(productRepository.save(product));
    }
    public ProductResponse addVarientToProduct(String productId,  List<VarientResponse> varientResponses) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        if (product.getVarients() == null) {
            product.setVarients(new ArrayList<>());
        }

        varientResponses.forEach(varientResponse -> {
            Varient varient = varientMapper.ResponsetoVarient(varientResponse);
            varient.setProduct(product);
            varientRepository.save(varient);
            product.getVarients().add(varient);
        });

        product.setUpdateat(LocalDateTime.now());
        return productMapper.toProductResponse(productRepository.save(product));
    }
    public ProductResponse PutProductDescription(String productId, List<DescriptionResponse> descriptionResponses){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        if (product.getVarients() == null) {
            product.setDescriptions(new ArrayList<>());
        }
        descriptionResponses.forEach(descriptionResponse -> {
            Description description = descriptionMapper.toResponsefromDescription(descriptionResponse);
            description.setProduct(product);
            product.getDescriptions().add(description);
        });
        product.setUpdateat(LocalDateTime.now());
        return productMapper.toProductResponse(productRepository.save(product));
    }
    public ProductResponse PutProductWithImage(ProductWithImageRequest request){
        Product product=productRepository.findById(request.getIdproduct()).
                orElseThrow(()->new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Image image= imageRepository.findById(request.getIdImage())
                .orElseThrow(()->new AppException(ErrorCode.IMAGE_NOT_FOUND));
        product.setImage(image);
        return productMapper.toProductResponse(productRepository.save(product));
    }
    public ProductResponse PutProduct(String id, Product_Update update){
        Product product= productRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productMapper.updateUpdate(product,update);
        product.setUpdateat(LocalDateTime.now());
        return productMapper.toProductResponse(productRepository.save(product));
    }
    public void DeleteProduct(String id){
        Product product= productRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        product.setDeleteat(LocalDateTime.now());
        product.setIsdeleted(true);
        productRepository.save(product);
    }
}
