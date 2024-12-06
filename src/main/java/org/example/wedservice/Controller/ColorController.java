package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.ColorRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.ColorResponse;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Form.Color_Update;
import org.example.wedservice.Service.ColorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colors")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ColorController {
    ColorService serviceColor;
    @GetMapping
    public ApiResponse<List<ColorResponse>> getall(){
        return ApiResponse.<List<ColorResponse>>builder()
                .Result(serviceColor.getall())
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<ColorResponse> getById(@PathVariable String id) {
        return ApiResponse.<ColorResponse>builder()
                .Result(serviceColor.getbyid(id))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @PostMapping
    public ApiResponse<ColorResponse> postColor(@RequestBody ColorRequest request) {
        return ApiResponse.<ColorResponse>builder()
                .Result(serviceColor.PostColor(request))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<ColorResponse> putColor(@PathVariable String id, @RequestBody Color_Update update)
            {
        return ApiResponse.<ColorResponse>builder()
                .Result(serviceColor.PutColor(id, update))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteColor(@PathVariable String id) {
        serviceColor.DeleteColor(id);
        return ApiResponse.<Void>builder()
                .message("Delete Completed")
                .code(0)
                .success(true)
                .build();
    }
}
