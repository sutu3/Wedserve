package org.example.wedservice.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.wedservice.Dto.Request.GenderRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.GenderResponse;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Service.GenderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class GenderController {
    GenderService genderService;
    @GetMapping
    public ApiResponse<List<GenderResponse>> getall(){
        return ApiResponse.<List<GenderResponse>>builder()
                .Result(genderService.getallGenderName())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping
    public ApiResponse<GenderResponse> postGender(@RequestBody GenderRequest request) {
        return ApiResponse.<GenderResponse>builder()
                .Result(genderService.Postgender(request))
                .code(0)
                .success(true)
                .message("Completed")
                .build();

    }
}
