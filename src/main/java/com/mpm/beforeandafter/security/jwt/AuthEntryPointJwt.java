package com.mpm.beforeandafter.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("response.getStatus()");
        System.out.println(response.getStatus());
        if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            System.out.println("zxc");
            logger.error("[ERROR] Not found error: {}", authException.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Error: Not found");
        } else if (response.getStatus() == HttpStatus.CONFLICT.value()) {
            // TODO: Fix duplicate response status
            System.out.println("qweqweqw");
            logger.error("[ERROR] Conflict error: {}", authException.getMessage());
            response.sendError(HttpServletResponse.SC_CONFLICT, "Error: Conflict");
        } else {
            logger.error("[ERROR] Unauthorized error: {}", authException.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
        }
    }
}
