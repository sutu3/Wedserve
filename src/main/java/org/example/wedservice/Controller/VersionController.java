package org.example.wedservice.Controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.UserRequest;
import org.example.wedservice.Dto.Request.VersionRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.UserResponse;
import org.example.wedservice.Dto.Response.VersionResponse;
import org.example.wedservice.Form.User_Update;
import org.example.wedservice.Service.VersionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/versions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@CrossOrigin(origins = "http://localhost:5173")
@Slf4j
public class VersionController {
    VersionService versionService;
    @GetMapping
    public ApiResponse<List<VersionResponse>> getall(){
        return ApiResponse.<List<VersionResponse>>builder()
                .Result(versionService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping
    public ApiResponse<VersionResponse> postVersion(@RequestBody VersionRequest request){
        return ApiResponse.<VersionResponse>builder()
                .Result(versionService.PostVersion(request))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }

}
