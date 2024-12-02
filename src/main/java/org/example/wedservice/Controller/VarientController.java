package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.VarientRequest;
import org.example.wedservice.Dto.Request.VersionRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.VarientResponse;
import org.example.wedservice.Dto.Response.VersionResponse;
import org.example.wedservice.Service.VarientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/varient")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@CrossOrigin(origins = "http://localhost:5173")
@Slf4j
public class VarientController {
    VarientService varientService;
    @GetMapping
    public ApiResponse<List<VarientResponse>> getall(){
        return ApiResponse.<List<VarientResponse>>builder()
                .Result(varientService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping
    public ApiResponse<VarientResponse> postVersion(@RequestBody VarientRequest request){
        return ApiResponse.<VarientResponse>builder()
                .Result(varientService.PostVarient(request))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable String id)  {
        varientService.deleteVarient(id);
        return ApiResponse.<Void>builder()
                .message("Delete Completed")
                .code(0)
                .success(true)
                .build();
    }
}
