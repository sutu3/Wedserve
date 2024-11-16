package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.VarientRequest;
import org.example.wedservice.Dto.Request.VersionRequest;
import org.example.wedservice.Dto.Response.VarientResponse;
import org.example.wedservice.Entity.*;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Mapper.*;
import org.example.wedservice.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class VarientService {
    private final CategoryMapper categoryMapper;
    SizeMapper sizeMapper;
    ColorMapper colorMapper;
    VarientMapper varientMapper;
    VersionMapper versionMapper;
    VarientRepository varientRepository;
    VarientMapper mapper;
    SizeRepository sizeRepository;
    ProductRepository productRepository;
    ColorRepository colorRepository;
    CategoryRepository categoryRepository;
    private final VersionRepository versionRepository;

    public List<VarientResponse> getall() {
        return varientRepository.findAll().stream().map(varientMapper::toVarientResponse).collect(Collectors.toList());
    }
    public  VarientResponse getbyId(String id){
        return mapper.toVarientResponse(varientRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.VARIENT_NOT_FOUND)));
    }
    public VarientResponse PostVarient(VarientRequest request){
        Product product=productRepository.findById(request.getIdproduct())
                .orElseThrow(()->new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        if(!sizeRepository.existsBySizename(request.getSizename()))
        {
            throw new AppException(ErrorCode.SIZE_NOT_FOUND);
        }
        Size size = sizeRepository.findBySizename(request.getSizename());
        if(!colorRepository.existsByColorname(request.getColorname()))
        {
            throw new AppException(ErrorCode.COLOR_NOT_FOUND);
        }
        Color color = colorRepository.findByColorname(request.getColorname());
        if(!categoryRepository.existsByName(request.getCategoryname()))
        {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        Category category = categoryRepository.findByName(request.getCategoryname());
       Varient varient= varientRepository.save(Varient.builder()
                .namevarient(category.getName() + " " + color.getColorname() + " " + size.getSizename())
                .color(color)
                .size(size)
                .product(product)
                .createat(LocalDateTime.now())
                .category(category)
                .build());
       Version version= Version.builder()
               .quantity_in_stock(0)
               .varient(varient)
               .originalprice(request.getOriginalprice())
               .selling_price(request.getSelling_price())
               .createat(LocalDateTime.now())
               .product(product)
               .isdeleted(false)
               .build();
        versionRepository.save(version);

        return mapper.toVarientResponse(varientRepository.findById(
                varient.getId_variant()).orElseThrow(()->new AppException(ErrorCode.VARIENT_NOT_FOUND)));
    }
/*    public SizeResponse putSize(String id, Size_Update update){
        Size sizeupdate= versionRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.SIZE_NOT_FOUND));
        mapper.UpdataSizename(sizeupdate,update);
        return mapper.toSizeResponse(versionRepository.save(sizeupdate));
    }
    public void deleteSize(String id) {
       versionRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.SIZE_NOT_FOUND));
        versionRepository.deleteById(id);
    }*/
}
