package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.contactdetails.service.ContactDetailsService;
import com.mpm.beforeandafter.email.service.EmailService;
import com.mpm.beforeandafter.exception.DuplicatedResourceException;
import com.mpm.beforeandafter.exception.ResourceNotFoundException;
import com.mpm.beforeandafter.role.model.Role;
import com.mpm.beforeandafter.role.repository.RoleRepository;
import com.mpm.beforeandafter.role.service.RoleService;
import com.mpm.beforeandafter.role.type.RoleType;
import com.mpm.beforeandafter.security.jwt.JwtUtils;
import com.mpm.beforeandafter.user.dto.*;
import com.mpm.beforeandafter.user.model.StatusType;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final EmailService emailService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final ContactDetailsService contactDetailsService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleService roleService,
                           EmailService emailService, UserMapper userMapper, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtUtils jwtUtils, ContactDetailsService contactDetailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.emailService = emailService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.contactDetailsService = contactDetailsService;
    }

    @Override
    public List<GetUserResponseDto> getUsers(RoleType roleType) {
        log.info("[ACTION] Fetching all users.");
        log.debug("[REQUEST] Provided role type from request: {}", roleType);
        List<User> users;
        if (roleType == null) {
            users = userRepository.findAll();
        } else {
            Role providedRole = roleRepository.findByName(roleType);
            users = userRepository.findAllByRolesIn(Set.of(providedRole.getId()));
        }
        log.debug("[RESPONSE] Getting all users (count): {}", users.size());
        log.info("[ACTION] Users with specified role successfully retrieved.");
        return users.stream()
                .map(userMapper::mapToGetUserResponseDto)
                .toList();
    }

    @Override
    public CreateUserResponseDto createUser(CreateUserRequestDto userDto, RoleType roleType) {
        //TODO: delete loggers when security developed and tested
        log.debug("[DETAILS] Provided user DTO: {}", userDto);
        log.debug("[DETAILS] Provided roleType: {}", roleType);
        log.info("[ACTION] Creating a new user.");

        Role role = roleService.findByName(roleType);
        User user = new User();
        user.setName(userDto.getUserName());
        user.setEmail(userDto.getUserEmail());
        user.setPassword(passwordEncoder.encode(userDto.getUserPassword()));
        try {
            user.setRoles(Set.of(role));
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException("No roles in database");
        }
        user.setStatus(StatusType.TO_REVIEW);
        User createdUser = saveUser(user);
        contactDetailsService.createAndGetDefaultContactDetails(createdUser.getId());

        emailService.sendRegistrationEmail(createdUser.getName(), createdUser.getEmail())
                .thenAcceptAsync(emailResponse -> {
                    if (emailResponse.sentSuccessfully()) {
                        log.info("Registration email sent successfully to {}", createdUser.getEmail());
                    } else {
                        log.error("Failed to send registration email to {}", createdUser.getEmail());
                    }
                });

        log.debug("[RESPONSE] New user created: {}", createdUser);
        log.info("[ACTION] User has been successfully created.");
        return userMapper.mapToCreateUserResponseDto(createdUser);
    }

    private User saveUser(User user) {
        User savedUser = null;
        try {
            savedUser = userRepository.save(user);

        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            log.warn("Could not execute statement. User already exists.");
            throw new DuplicatedResourceException("User already exists.");
        }
        return savedUser;
    }

    @Override
    public GetUserResponseDto getUserById(Long userId) {
        log.info("[ACTION] Getting user by id.");
        log.debug("[REQUEST] Provided user id from request: {}", userId);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> {
                    log.warn("There is no user with the given id: {}", userId);
                    return new ResourceNotFoundException(
                            "User not found with id: {}" + userId);
                });
        log.debug("[RESPONSE] User with id: {} has been successfully retrieved: {}", userId, user);
        log.info("[ACTION] Getting user by id completed successfully.");
        return userMapper.mapToGetUserResponseDto(user);
    }

    @Override
    public GetAboutMeResponseDto getAboutMeByUserId(Long userId) {
        log.info("[ACTION] Getting user about info.");
        log.debug("[REQUEST] Provided user id from request: {}", userId);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> {
                    log.warn("There is no user with the given id: {}", userId);
                    return new ResourceNotFoundException(
                            "User not found with id: {}" + userId);
                });
        log.debug("[RESPONSE] User about info for user id: {} has been successfully retrieved: {}", userId, user);
        log.info("[ACTION] Getting user about info by user id completed successfully.");
        return userMapper.mapToGetAboutMeResponseDto(user);
    }

    @Override
    public CreateAboutMeResponseDto updateUserByAboutMe(Long userId, CreateAboutMeRequestDto aboutMe) {
        log.info("[ACTION] Updating about user info.");
        log.debug("[REQUEST] Provided user id from request: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.warn("There is no user with the given id: {}", userId);
                    return new ResourceNotFoundException(
                            "User not found with id: {}" + userId);
                });
        user.setAboutMe(aboutMe.getAboutMe());
        userRepository.save(user);
        log.debug("[RESPONSE] User about info for user id: {} has been successfully updated: {}", userId, user);
        log.info("[ACTION] Updating user about info completed successfully.");
        return userMapper.mapToCreateAboutMeResponseDto(user);
    }

    @Override
    public CreateUserResponseDto updateUser(Long userId, CreateUserRequestDto userDto) {
        log.info("[ACTION] Updating user details.");
        log.debug("[REQUEST] Provided user id from request: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.warn("There is no user with the given id: {}", userId);
                    return new ResourceNotFoundException(
                            "User not found with id: {}" + userId);
                });
        user.setName(userDto.getUserName());
        userRepository.save(user);
        log.debug("[RESPONSE] User details for user id: {} has been successfully updated: {}", userId, user);
        log.info("[ACTION] Updating user details completed successfully.");
        return userMapper.mapToCreateUserResponseDto(user);
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("[ACTION] Deleting specified user.");
        log.debug("[REQUEST] Deleting user with id: {}", userId);
        userRepository
                .findById(userId)
                .ifPresentOrElse(user -> {
                            userRepository.delete(user);
                            log.info("User with id: {} deleted successfully", userId);
                        },
                        () -> {
                            log.warn( "There isn't user with the given ID: {}", userId);
                            throw new ResourceNotFoundException("There isn't user with the given ID: " + userId);
                        });
        log.debug("[RESPONSE] User with id: {} has been deleted.", userId);
        log.info("[ACTION] Deleting user completed successfully.");
    }

    @Override
    public CreateAvatarResponseDto createAvatar(MultipartFile file, CreateAvatarRequestDto request)
            throws FileUploadException {
        log.info("[ACTION] Adding user avatar.");

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> {
                    log.warn("There is no user with the given id: {}", request.getUserId());
                    return new ResourceNotFoundException(
                            "User not found with id: {}" + request.getUserId());
                });

        log.debug("[REQUEST] Adding avatar for specified user: {}", user);

        try {
            user.setAvatar(file.getBytes());
        } catch (IOException e) {
            throw new FileUploadException("File not uploaded.");
        }

        userRepository.save(user);
        log.debug("[RESPONSE] Avatar for user: {} has been added.", user);
        log.info("[ACTION] Adding of avatar completed successfully.");
        return userMapper.mapToCreateAvatarResponseDto(user);
    }

    public Authentication getAuthentication(SignInRequestDto loginRequest) {
        //TODO: delete logger when security developed
        log.info("[ACTION] Getting authentication.");
        log.debug("[REQUEST] Getting authentication for  request: {}", loginRequest);
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUserEmail(),
                                loginRequest.getUserPassword()));
        return authentication;
    }

    @Override
    public String getSecurityContextAndJwt(SignInRequestDto loginRequest, Authentication authentication) {
        log.info("[ACTION] Getting security context and jwt.");
        String userEmail = loginRequest.getUserEmail();
        com.mpm.beforeandafter.user.model.User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + userEmail));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateJwtToken(authentication, user.getId());
    }

    @Override
    public org.springframework.security.core.userdetails.User getUserDetails(Authentication authentication) {
        return (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
    }

    @Override
    public List<String> getRoles(org.springframework.security.core.userdetails.User userDetails) {
        return userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .toList();
    }

    @Override
    public SignInResponseDto createJwtResponse(String jwt, String username, List<String> roles) {
        return new SignInResponseDto(jwt, username, roles);
    }
}
