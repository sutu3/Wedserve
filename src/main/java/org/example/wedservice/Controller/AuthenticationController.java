package org.example.wedservice.Controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.AuthenticationRequest;
import org.example.wedservice.Dto.Request.IntrospectRequest;
import org.example.wedservice.Dto.Response.ApiResponse;
import org.example.wedservice.Dto.Response.AuthenticationResponse;
import org.example.wedservice.Dto.Response.IntrospectResponse;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/authentication")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request)
            throws AppException, JOSEException {
        var result=authenticationService.isAuthenticated(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .Result(result)
                .success(true)
                .code(0)
                .build();
    }
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authentication(@RequestBody IntrospectRequest request)
            throws AppException, JOSEException, ParseException {
        var result=authenticationService.instrospect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .Result(result)
                .message("Completed")
                .success(true)
                .code(0)
                .build();
    }
}
