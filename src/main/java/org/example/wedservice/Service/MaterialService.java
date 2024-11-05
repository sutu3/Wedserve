package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.CategoryRequest;
import org.example.wedservice.Dto.Request.MaterialRequest;
import org.example.wedservice.Dto.Response.MaterialResponse;
import org.example.wedservice.Entity.Category;
import org.example.wedservice.Entity.Material;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Category_Update;
import org.example.wedservice.Form.Material_Update;
import org.example.wedservice.Mapper.MaterialMapper;
import org.example.wedservice.Repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class MaterialService {
    MaterialRepository materialRepository;
    MaterialMapper materialMapper;

    public List<MaterialResponse> getall(){
        return materialRepository.findAll().stream().map(materialMapper::toMaterialResponse)
                .collect(Collectors.toList());
    }
    public MaterialResponse Getbyid(String id) throws AppException {
        return materialMapper.toMaterialResponse(materialRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.MATERIAL_NOT_FOUND)));
    }
    public MaterialResponse PostMaterial(MaterialRequest request) throws AppException {
        Material material= materialMapper.toMaterial(request);
        log.info(materialRepository.existsByName(material.getName())?"dung":"sai");
        if(materialRepository.existsByName(material.getName())){
            throw new AppException(ErrorCode.MATERIAL_IS_EXITED);
        }
        ;
        return materialMapper.toMaterialResponse(materialRepository.save(material.builder()
                .isdeleted(false)
                .createat(LocalDateTime.now())
                .build()));
    }
    public MaterialResponse GetbyName(MaterialRequest request) throws AppException {
        if(materialRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.MATERIAL_NOT_FOUND);
        }
        return materialMapper.toMaterialResponse(
                materialRepository.findFistByName(request.getName()));
    }
    public MaterialResponse PutMaterial(String id, Material_Update update) throws AppException {
        Material material= materialRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.MATERIAL_NOT_FOUND));
        materialMapper.updateMaterial(material,update);
        material.setUpdateat(LocalDateTime.now());
        return materialMapper.toMaterialResponse(materialRepository.save(material));
    }
    public void DeleteMaterial(String id) throws AppException {
        Material material= materialRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.MATERIAL_NOT_FOUND));
        materialRepository.save(
                material.builder()
                .deleteat(LocalDateTime.now())
                .isdeleted(true)
                .build());
    }
}
