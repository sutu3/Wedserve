package org.example.wedservice.Controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.SizeRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.SizeResponse;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Form.Size_Update;
import org.example.wedservice.Service.SizeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sizes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class SizeController {
    SizeService sizeservice;
    @GetMapping
    public ApiResponse<List<SizeResponse>> getall(){
        return ApiResponse.<List<SizeResponse>>builder()
                .Result(sizeservice.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<SizeResponse> getbyId(@PathVariable String id) {
        return ApiResponse.<SizeResponse>builder()
                .Result(sizeservice.getbyId(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping
    public ApiResponse<SizeResponse> postSize(@RequestBody @Valid SizeRequest request){
        log.info(request.getSizename().toString());
        return ApiResponse.<SizeResponse>builder()
                .Result(sizeservice.PostSize(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<SizeResponse> putSize(@PathVariable String id, @RequestBody Size_Update update)
            {
        return ApiResponse.<SizeResponse>builder()
                .Result(sizeservice.putSize(id, update))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteSize(@PathVariable String id)  {
        sizeservice.deleteSize(id);
        return ApiResponse.<Void>builder()
                .message("Delete Completed")
                .code(0)
                .success(true)
                .build();
    }
}
