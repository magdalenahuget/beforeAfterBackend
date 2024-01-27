package com.mpm.beforeandafter.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpm.beforeandafter.BeforeAndAfterApplication;
import com.mpm.beforeandafter.role.type.RoleType;
import com.mpm.beforeandafter.user.dto.CreateUserRequestDto;
import com.mpm.beforeandafter.user.dto.CreateUserResponseDto;
import com.mpm.beforeandafter.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = BeforeAndAfterApplication.class)
public class UserAuthIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() throws Exception {
        // GIVEN
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setUserName("John Doe");
        requestDto.setUserEmail("john.doe@example.com");
        requestDto.setUserPassword("password123");

        CreateUserResponseDto responseDto = CreateUserResponseDto.builder()
                .userName("John Doe")
                .email("john.doe@example.com")
                .roles(List.of(RoleType.ROLE_USER.name()))
                .build();

        String fakeJwtToken = "fake-jwt-token";

        when(userService.createUser(requestDto, RoleType.ROLE_USER)).thenReturn(responseDto);

        // WHEN
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .header("Authorization", "Bearer " + fakeJwtToken))
                .andExpect(status().isOk())
                .andReturn();

        // THEN
        assertEquals(result.getResponse().getStatus(), 200);
        verify(userService, times(1)).createUser(requestDto, RoleType.ROLE_USER);
    }
}
