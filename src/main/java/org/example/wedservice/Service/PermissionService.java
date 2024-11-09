package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.PermissionRequest;
import org.example.wedservice.Dto.Response.PermissionResponse;
import org.example.wedservice.Entity.Permission;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Mapper.PermissionMapper;
import org.example.wedservice.Repository.PermissonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class PermissionService {

    PermissonRepository permissonRepository;
    PermissionMapper mapper;
    public List<PermissionResponse> getall() {
        return permissonRepository.findAll().stream()
                .map(mapper::toPermissionResponse).collect(Collectors.toList());
    }
    public PermissionResponse PostPermission(PermissionRequest request){
        Permission permission=mapper.toPermission(request);
        if(permissonRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.PERMISSION_IS_EXITED);
        }
        return mapper.toPermissionResponse(permissonRepository.save(permission));
    }

    public void deletePermission(String name) {
        permissonRepository.findById(name)
                .orElseThrow(()->new AppException(ErrorCode.PERMISSION_NOT_FOUND));
        permissonRepository.deleteById(name);
    }
}
