package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.SizeRequest;
import org.example.wedservice.Dto.Request.VersionRequest;
import org.example.wedservice.Dto.Response.SizeResponse;
import org.example.wedservice.Dto.Response.VersionResponse;
import org.example.wedservice.Entity.Product;
import org.example.wedservice.Entity.Size;
import org.example.wedservice.Entity.Version;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Size_Update;
import org.example.wedservice.Mapper.SizeMapper;
import org.example.wedservice.Mapper.VersionMapper;
import org.example.wedservice.Repository.ProductRepository;
import org.example.wedservice.Repository.VersionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class VersionService {

    VersionRepository versionRepository;
    VersionMapper mapper;
    ProductRepository productRepository;
    public List<VersionResponse> getall() {
        return versionRepository.findAll().stream()
                .map(mapper::toVersionResponse).collect(Collectors.toList());
    }
    public  VersionResponse getbyId(String id){

        return mapper.toVersionResponse(versionRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.SIZE_NOT_FOUND)));
    }
    public VersionResponse PostVersion(VersionRequest request){
        Product product=productRepository.findById(request.getIdproduct())
                .orElseThrow(()->new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Version version=mapper.toVersion(request);
        version.setQuantity_in_stock(0);
        version.setCreateat(LocalDateTime.now());
        version.setProduct(product);

        return mapper.toVersionResponse(versionRepository.save(version));
    }
//    public SizeResponse putSize(String id, Size_Update update){
//        Size sizeupdate= versionRepository.findById(id)
//                .orElseThrow(()->new AppException(ErrorCode.SIZE_NOT_FOUND));
//        mapper.UpdataSizename(sizeupdate,update);
//        return mapper.toSizeResponse(versionRepository.save(sizeupdate));
//    }
//    public void deleteSize(String id) {
//        versionRepository.findById(id)
//                .orElseThrow(()->new AppException(ErrorCode.SIZE_NOT_FOUND));
//        versionRepository.deleteById(id);
//    }
}
