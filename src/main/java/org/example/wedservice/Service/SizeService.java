package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.SizeRequest;
import org.example.wedservice.Dto.Response.SizeResponse;
import org.example.wedservice.Entity.Size;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Size_Update;
import org.example.wedservice.Mapper.SizeMapper;
import org.example.wedservice.Repository.SizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class SizeService {

    SizeRepository sizerepository;
    SizeMapper mapper;
    public List<SizeResponse> getall() {
        return sizerepository.findAll().stream()
                .map(mapper::toSizeResponse).collect(Collectors.toList());
    }
    public  SizeResponse getbyId(String id) throws AppException {

        return mapper.toSizeResponse(sizerepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.SIZE_NOT_FOUND)));
    }
    public SizeResponse PostSize(SizeRequest request) throws AppException {
        Size size=mapper.toSize(request);
        if(sizerepository.existsBySizename(request.getSizename())){
            throw new AppException(ErrorCode.SIZE_IS_EXITED);
        }
        return mapper.toSizeResponse(sizerepository.save(size));
    }
    public SizeResponse putSize(String id, Size_Update update) throws AppException {
        Size sizeupdate=sizerepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.SIZE_NOT_FOUND));
        mapper.UpdataSizename(sizeupdate,update);
        return mapper.toSizeResponse(sizerepository.save(sizeupdate));
    }
    public void deleteSize(String id) throws AppException {
        sizerepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.SIZE_NOT_FOUND));
        sizerepository.deleteById(id);
    }
}
