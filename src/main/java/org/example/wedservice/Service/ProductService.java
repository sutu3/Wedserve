package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Dto.Request.MaterialRequest;
import org.example.wedservice.Dto.Request.ProductRequest;
import org.example.wedservice.Dto.Request.ProductWithImageRequest;
import org.example.wedservice.Dto.Response.MaterialResponse;
import org.example.wedservice.Dto.Response.ProductResponse;
import org.example.wedservice.Dto.Response.VarientResponse;
import org.example.wedservice.Entity.Image;
import org.example.wedservice.Entity.Material;
import org.example.wedservice.Entity.Product;
import org.example.wedservice.Entity.Varient;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Material_Update;
import org.example.wedservice.Form.Product_Update;
import org.example.wedservice.Mapper.MaterialMapper;
import org.example.wedservice.Mapper.ProductMapper;
import org.example.wedservice.Mapper.VarientMapper;
import org.example.wedservice.Repository.ImageRepository;
import org.example.wedservice.Repository.MaterialRepository;
import org.example.wedservice.Repository.ProductRepository;
import org.example.wedservice.Repository.VarientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        product.setMaterials(materialRepository.findByName(request.getMaterialName()));
        product.setCreateat(LocalDateTime.now());
        if(productRepository.existsByName(product.getName())){
            throw new AppException(ErrorCode.PRODUCT_IS_EXITED);
        }
        return productMapper.toProductResponse(productRepository.save(product));
    }
    public ProductResponse addVarientToProduct(String productId, VarientResponse varient) {
        Varient varientConver= varientMapper.ResponsetoVarient(varient);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PURCHASE_NOT_FOUND));
        // Thiết lập quan hệ giữa varient và product
        varientConver.setProduct(product);
        // Lưu variant mới
        varientRepository.save(varientConver);
        // Thêm variant vào danh sách varients của product
        product.getVarients().add(varientConver);
        // Cập nhật thời gian sửa đổi sản phẩm
        product.setUpdateat(LocalDateTime.now());
        // Lưu product sau khi thêm variant
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
