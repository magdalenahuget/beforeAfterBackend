package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.contactdetails.model.ContactDetails;
import com.mpm.beforeandafter.contactdetails.service.ContactDetailsService;
import com.mpm.beforeandafter.email.service.EmailService;
import com.mpm.beforeandafter.email.service.EmailServiceImpl;
import com.mpm.beforeandafter.role.model.Role;
import com.mpm.beforeandafter.role.service.RoleService;
import com.mpm.beforeandafter.role.type.RoleType;
import com.mpm.beforeandafter.user.dto.CreateUserRequestDto;
import com.mpm.beforeandafter.user.dto.CreateUserResponseDto;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @Mock
    private ContactDetailsService contactDetailsService;

    @Mock
    private EmailServiceImpl emailService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // GIVEN
        CreateUserRequestDto userRequestDto = new CreateUserRequestDto();
        userRequestDto.setUserName("John Doe");
        userRequestDto.setUserEmail("john.doe@example.com");
        userRequestDto.setUserPassword("password123");

        RoleType roleType = RoleType.ROLE_USER;
        Role role = new Role();
        role.setName(roleType);

        CreateUserResponseDto userResponse = new CreateUserResponseDto();
        userResponse.setUserName("John Doe");
        userResponse.setEmail("john.doe@example.com");
        userResponse.setRoles(List.of(RoleType.ROLE_USER.name()));
        ContactDetails contactDetails = new ContactDetails();

        User user =  new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

//        contactDetails.setUser(userResponse);
//        contactDetailsRepository.save(contactDetails);

        // WHEN
        when(roleService.findByName(roleType)).thenReturn(role);
        when(passwordEncoder.encode(userRequestDto.getUserPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenReturn(user);
        when(userMapper.mapToCreateUserResponseDto(user)).thenReturn(userResponse);
        EmailService emailServiceMock = mock(EmailService.class);
        doNothing().when(emailServiceMock).handleSendRegisterEmail(user);
//        doNothing().when(userService).sendEmail(mockUser);

        //        doNothing().when(userService.sendEmail(userResponse));
//        when(emailService.sendRegistrationEmail(userResponse.getName(), userResponse.getEmail())).thenReturn(userResponse.getName());

        CreateUserResponseDto createUserResponseDto = userService.createUser(userRequestDto, roleType);
        System.out.println("createUserResponseDto = " + createUserResponseDto);
        System.out.println("userRepository.findByName(user.getName()) = " + userRepository.findByName(user.getName()));


        verify(roleService, times(1)).findByName(roleType);
        verify(passwordEncoder, times(1)).encode(userRequestDto.getUserPassword());
        verify(userRepository, times(1)).save(any());
//        verify(userMapper, times(1)).mapToCreateUserResponseDto(userResponse);

        assertEquals("John Doe", createUserResponseDto.getUserName());
        assertEquals("john.doe@example.com", createUserResponseDto.getEmail());
    }
}