package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.role.type.RoleType;
import com.mpm.beforeandafter.user.dto.*;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    List<GetUserResponseDto> getUsers(RoleType roleType);

    CreateUserResponseDto createUser(CreateUserRequestDto userDto, RoleType roleType);

    CreateUserResponseDto updateUser(Long userId, CreateUserRequestDto userDto);

    GetUserResponseDto getUserById(Long userId);

    GetAboutMeResponseDto getAboutMeByUserId(Long userId);

    CreateAboutMeResponseDto updateUserByAboutMe(Long userId, CreateAboutMeRequestDto aboutMe);

    void deleteUser(Long userId);

    CreateAvatarResponseDto createAvatar(MultipartFile file, CreateAvatarRequestDto request) throws FileUploadException;
}
