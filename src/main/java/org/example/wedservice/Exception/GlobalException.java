package org.example.wedservice.Exception;

import org.example.wedservice.Dto.Response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalException {
    public GlobalException(){

    }
    @ExceptionHandler(value=RuntimeException.class)
    public ApiResponse<AppException> handleException(RuntimeException e) {
        return ApiResponse.<AppException>builder()
                .code(ErrorCode.UNCATEGORY.getCode())
                .success(false)
                .message(ErrorCode.UNCATEGORY.getMessage())
                .build();
    }
    @ExceptionHandler(value=AppException.class)
    public ApiResponse<AppException> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
    return ApiResponse.<AppException>builder()
            .message(errorCode.getMessage())
            .code(errorCode.getCode())
            .success(false)
            .build();
    }
}
