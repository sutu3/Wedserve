package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.VarientRequest;
import org.example.wedservice.Dto.Request.VersionRequest;
import org.example.wedservice.Dto.Response.*;
import org.example.wedservice.Entity.Product;
import org.example.wedservice.Entity.Size;
import org.example.wedservice.Entity.Version;
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

    VarientRepository varientRepository;
    VarientMapper mapper;
    SizeRepository sizeRepository;
    ProductRepository productRepository;
    ColorRepository colorRepository;
    CategoryRepository categoryRepository;

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
        SizeResponse size= sizeMapper.toSizeResponse(
                sizeRepository.findBySizename(request.getSizename()));
        if(!colorRepository.existsByColorname(request.getColorname()))
        {
            throw new AppException(ErrorCode.COLOR_NOT_FOUND);
        }
        ColorResponse color=colorMapper.toColorResponse(
                colorRepository.findByColorname(request.getColorname()));
        if(!categoryRepository.existsByName(request.getCategoryname()))
        {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        CategoryResponse category=categoryMapper.toCategoryResponse(
                categoryRepository.findByName(request.getCategoryname()));
        return mapper.toVersionResponse(varientRepository.save(version));
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
