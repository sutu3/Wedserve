package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Dto.Request.CategoryRequest;
import org.example.wedservice.Dto.Response.CategoryResponse;
import org.example.wedservice.Entity.Category;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.Category_Update;
import org.example.wedservice.Mapper.CategoryMapper;
import org.example.wedservice.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public List<CategoryResponse> getall(){
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse)
                .collect(Collectors.toList());
    }
    public CategoryResponse getbyid(String id) throws AppException {
        return categoryMapper.toCategoryResponse(categoryRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.CATEGORY_NOT_FOUND)));
    }
    public CategoryResponse postCategory(CategoryRequest request){
        Category category=categoryMapper.toCategory(request);
        if(categoryRepository.existsByName(category.getName())){
             new AppException(ErrorCode.CATEGORY_IS_EXITED);
        }
        category.setIsdeleted(false);
        category.setCreateat(LocalDateTime.now());
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }
    public CategoryResponse putCategory(String id, Category_Update update) throws AppException {
        Category category=categoryRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryMapper.updateCategory(category,update);
        category.setUpdateat(LocalDateTime.now());
        return categoryMapper.toCategoryResponse(categoryRepository.save(category)) ;
    }
    public void DeleteCategory(String id) throws AppException {
        Category category=categoryRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.CATEGORY_NOT_FOUND));
       categoryRepository.save(category.builder()
               .deleteat(LocalDateTime.now())
               .isdeleted(true)
               .build());
    }
}
