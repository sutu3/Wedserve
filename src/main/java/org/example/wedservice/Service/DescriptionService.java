package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.DescriptionRequest;
import org.example.wedservice.Dto.Response.DescriptionResponse;
import org.example.wedservice.Entity.Description;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Description_Update;
import org.example.wedservice.Mapper.DescriptionMapper;
import org.example.wedservice.Repository.DescriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class DescriptionService {
    DescriptionRepository descriptionRepository;
    DescriptionMapper descriptionMapper;

    public List<DescriptionResponse> getallDes(){
        return descriptionRepository.findAll()
                .stream().map(descriptionMapper::toDescriptionResponse)
                .collect(Collectors.toUnmodifiableList());
    }
    public DescriptionResponse getById(String id) throws AppException {
        return descriptionMapper.toDescriptionResponse(descriptionRepository
                .findById(id).orElseThrow(() ->
                        new AppException(ErrorCode.DESCRIPTION_NOT_FOUND)));
    }
    public DescriptionResponse PostDescription(DescriptionRequest request){
        Description description=descriptionMapper.toDescription(request);
        return descriptionMapper.
                toDescriptionResponse(descriptionRepository.save(description));
    }
    public DescriptionResponse PutDescription(String id, Description_Update update)
            throws AppException {

        Description description=descriptionRepository.
                findById(id).orElseThrow(()->
                        new AppException(ErrorCode.DESCRIPTION_NOT_FOUND));
                description.setUpdateat(LocalDate.now());
        descriptionMapper.updateDescription(description,update);
        return descriptionMapper.toDescriptionResponse(
                descriptionRepository.save(description));
    }
    public void DeleteDescription(String id) throws AppException {
        Description description=descriptionRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.DESCRIPTION_NOT_FOUND));
        description.builder()
                .deleteat(LocalDate.now())
                .isdeleted(true);
    }

}
