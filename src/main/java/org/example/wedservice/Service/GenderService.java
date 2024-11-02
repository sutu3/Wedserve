package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.GenderRequest;
import org.example.wedservice.Dto.Response.GenderResponse;
import org.example.wedservice.Entity.Gender;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Mapper.GenderMapper;
import org.example.wedservice.Repository.GenderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class GenderService {
    GenderMapper genderMapper;
    GenderRepository genderRepository;
    public List<GenderResponse> getallGenderName() {
        return genderRepository.findAll()
                .stream().map(genderMapper::toGenderResponse)
                .collect(Collectors.toList());
    }
    public GenderResponse Postgender(GenderRequest request) throws AppException {
        if(genderRepository.existsByGender(request.getGender())){
            throw new AppException(ErrorCode.GENDER_IS_EXITED);
        }
        Gender gender=genderMapper.togender(request);
        return genderMapper.toGenderResponse(genderRepository.save(gender));
    }
}
