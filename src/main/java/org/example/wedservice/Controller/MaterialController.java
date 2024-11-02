package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.MaterialRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.MaterialResponse;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Form.Material_Update;
import org.example.wedservice.Service.MaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class MaterialController {
    MaterialService materialService;
    @GetMapping
    public ApiResponse<List<MaterialResponse>> getall(){
        return ApiResponse.<List<MaterialResponse>>builder()
                .Result(materialService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @GetMapping("/name")
    public ApiResponse<MaterialResponse> getByName(@RequestBody MaterialRequest request) throws AppException {
        return ApiResponse.<MaterialResponse>builder()
                .Result(materialService.GetbyName(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<MaterialResponse> getById(@PathVariable String id) throws AppException {
        return ApiResponse.<MaterialResponse>builder()
                .Result(materialService.Getbyid(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping()
    public ApiResponse<MaterialResponse> postMaterial(@RequestBody MaterialRequest request) throws AppException {
        return ApiResponse.<MaterialResponse>builder()
                .Result(materialService.PostMaterial(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<MaterialResponse> putMaterial(@PathVariable String id, @RequestBody Material_Update update)
            throws AppException {
        return ApiResponse.<MaterialResponse>builder()
                .Result(materialService.PutMaterial(id,update))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable String id) throws AppException {
        materialService.DeleteMaterial(id);
        return ApiResponse.<Void>builder()
               .message("Delete Completed")
               .code(0)
               .success(true)
               .build();
    }
}
