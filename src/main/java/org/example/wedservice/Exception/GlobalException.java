package org.example.wedservice.Exception;

import org.example.wedservice.Dto.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class GlobalException {
    public GlobalException(){

    }
    @ExceptionHandler(value=RuntimeException.class)
    ResponseEntity<ApiResponse> handleException(RuntimeException e) {
        ApiResponse apiResponse=ApiResponse.builder()
                .success(false)
                .code(ErrorCode.UNAUTHENTICATED.getCode())
                .message(ErrorCode.UNAUTHENTICATED.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);

    }
    @ExceptionHandler(value=AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
    return  ResponseEntity.status(errorCode.getStatus())
            .body(ApiResponse.<AppException>builder()
            .message(errorCode.getMessage())
            .code(errorCode.getCode())
            .success(false)
            .build());
    }
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handleAccessDenied(AccessDeniedException e) {
        ErrorCode error = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(error.getStatus())
                .body(ApiResponse.<AppException>builder()
                .message(error.getMessage())
                .code(error.getCode())
                .success(false)
                .build())
                ;
    }
}
