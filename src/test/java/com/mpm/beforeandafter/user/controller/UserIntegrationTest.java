package com.mpm.beforeandafter.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpm.beforeandafter.role.type.RoleType;
import com.mpm.beforeandafter.user.dto.CreateUserRequestDto;
import com.mpm.beforeandafter.user.dto.CreateUserResponseDto;
import com.mpm.beforeandafter.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "src/main/resources/application-test.yaml")
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        // GIVEN
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setUserName("testUser");
        requestDto.setUserEmail("testEmail");
        requestDto.setUserPassword("testPassword");

        CreateUserResponseDto responseDto = new CreateUserResponseDto();
        responseDto.setUserName(requestDto.getUserName());
        responseDto.setEmail(requestDto.getUserEmail());
        responseDto.setRoles(List.of(RoleType.ROLE_USER.name()));

        when(userService.createUser(any(), any())).thenReturn(responseDto);

        // WHEN
        ResultActions resultActions = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        // THEN

    }
}
