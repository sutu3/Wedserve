package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.CategoryRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.CategoryResponse;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Form.Category_Update;
import org.example.wedservice.Service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorys")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CategoryController {
    CategoryService categoryService;
    @GetMapping
    public ApiResponse<List<CategoryResponse>> getall(){
        return ApiResponse.<List<CategoryResponse>>builder()
                .Result(categoryService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getById(@PathVariable String id) {
        return ApiResponse.<CategoryResponse>builder()
                .Result(categoryService.getbyid(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping()
    public ApiResponse<CategoryResponse> PostCategory(@RequestBody CategoryRequest request){
        return ApiResponse.<CategoryResponse>builder()
                .Result(categoryService.postCategory(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> PutCategory(@PathVariable String id, @RequestBody Category_Update update)
            {
        return ApiResponse.<CategoryResponse>builder()
                .Result(categoryService.putCategory(id,update))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable String id) {
        categoryService.DeleteCategory(id);
        return ApiResponse.<Void>builder()
               .message("Delete Completed")
               .code(0)
               .success(true)
               .build();
    }
}
