package org.example.wedservice.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.wedservice.Dto.Request.SizeRequest;
import org.example.wedservice.Dto.Request.UserRequest;
import org.example.wedservice.Dto.Response.SizeResponse;
import org.example.wedservice.Dto.Response.UserResponse;
import org.example.wedservice.Entity.Size;
import org.example.wedservice.Entity.User;
import org.example.wedservice.Enum.Role;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Form.User_Update;
import org.example.wedservice.Mapper.UserMapper;
import org.example.wedservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

    UserRepository userRepository;
    UserMapper mapper;


    PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getall() {
        return userRepository.findAll().stream()
                .map(mapper::toUserResponse).collect(Collectors.toList());
    }

    public UserResponse getmyInfor() throws AppException {
        var context= SecurityContextHolder.getContext();
        String nameuser=context.getAuthentication().getName();
        return mapper.toUserResponse(userRepository.findByUsername(nameuser)
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND)));
    }
    @PostAuthorize("returnObject.username==authentication.name")
    public UserResponse getbyId(String id) throws AppException {
        return mapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse PostUser(UserRequest request) throws AppException {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_IS_EXITED);
        }
        User user = mapper.toUser(request);
        HashSet<String> role=new HashSet<String>();
        role.add(Role.USER.name());
        return mapper.toUserResponse(userRepository
                .save(user.builder()
                        .username(request.getUsername())
                        .email(request.getEmail())
                        .dob(request.getDob())
                        .fullname(request.getFullname())
                        .phonenumber(request.getPhonenumber())
                        .gender(request.getGender())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdat(LocalDateTime.now())
                .isDeleted(false)
                .roles(role)
                .build()));
    }

    public UserResponse putUser(String id, User_Update update) throws AppException {
        User userupdate = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        mapper.updateUser(userupdate, update);
        return mapper.toUserResponse(userRepository
                .save(userupdate.builder()
                .updatedat(LocalDateTime.now())
                .isDeleted(false)
                .build()));
    }

    public void deleteUser(String id) throws AppException {
        User user=userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userRepository
                .save(user.builder()
                        .deletedat(LocalDateTime.now())
                        .isDeleted(true)
                        .build());
    }
}
