package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.ColorRequest;
import org.example.wedservice.Dto.Request.DescriptionRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.ColorResponse;
import org.example.wedservice.Dto.Response.DescriptionResponse;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Form.Color_Update;
import org.example.wedservice.Form.Description_Update;
import org.example.wedservice.Service.DescriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/description")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class DescriptionController {
    DescriptionService descriptionService;
    @GetMapping
    public ApiResponse<List<DescriptionResponse>> getall(){
        return ApiResponse.<List<DescriptionResponse>>builder()
                .Result(descriptionService.getallDes())
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<DescriptionResponse> getById(@PathVariable String id) throws AppException {
        return ApiResponse.<DescriptionResponse>builder()
                .Result(descriptionService.getById(id))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @PostMapping
    public ApiResponse<DescriptionResponse> postColor(@RequestBody DescriptionRequest request) throws AppException {
        return ApiResponse.<DescriptionResponse>builder()
                .Result(descriptionService.PostDescription(request))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<DescriptionResponse> putColor(@PathVariable String id, @RequestBody Description_Update update)
            throws AppException {
        return ApiResponse.<DescriptionResponse>builder()
                .Result(descriptionService.PutDescription(id, update))
                .message("Completed")
                .code(0)
                .success(true)
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteDescription(@PathVariable String id) throws AppException {
        descriptionService.DeleteDescription(id);
        return ApiResponse.<Void>builder()
                .message("Delete Completed")
                .code(0)
                .success(true)
                .build();
    }
}
