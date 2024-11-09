package org.example.wedservice.Controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.SizeRequest;
import org.example.wedservice.Dto.Request.UserRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.SizeResponse;
import org.example.wedservice.Dto.Response.UserResponse;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Form.Size_Update;
import org.example.wedservice.Form.User_Update;
import org.example.wedservice.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;
    @GetMapping
    public ApiResponse<List<UserResponse>> getall(){
        return ApiResponse.<List<UserResponse>>builder()
                .Result(userService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }

    @GetMapping("/myinfor")
    public ApiResponse<UserResponse> getmyinfor(){
        return ApiResponse.<UserResponse>builder()
                .Result(userService.getmyInfor())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getbyId(@PathVariable String id) {
        return ApiResponse.<UserResponse>builder()
                .Result(userService.getbyId(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping
    public ApiResponse<UserResponse> postSize(@RequestBody @Valid UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .Result(userService.PostUser(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> putSize(@PathVariable String id, @RequestBody User_Update update)
            {
        return ApiResponse.<UserResponse>builder()
                .Result(userService.putUser(id, update))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteSize(@PathVariable String id){
        userService.deleteUser(id);
        return ApiResponse.<Void>builder()
                .message("Delete Completed")
                .code(0)
                .success(true)
                .build();
    }
}
