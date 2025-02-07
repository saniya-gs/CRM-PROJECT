package com.ourdept.crm_software.company.security.exceptionHandling;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ourdept.crm_software.crm.utils.ApiResponse;
import com.ourdept.crm_software.crm.utils.ErrorDetails;
import com.ourdept.crm_software.crm.utils.HttpStatusCodes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom implementation of AuthenticationEntryPoint to handle authentication failures for requests requiring authentication.
 */
public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Handles authentication failure, constructing and sending a custom ApiResponse when a request requires authentication.
     *
     * @param request       the HttpServletRequest being processed
     * @param response      the HttpServletResponse to send the response to
     * @param authException the exception that caused the authentication to fail
     * @throws IOException      if an input or output error occurs
     * @throws ServletException if the request could not be handled
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        // Get the current timestamp to include in the response
        LocalDateTime currentTimeStamp = LocalDateTime.now();

        // Extract the error message from the AuthenticationException, or use a default message
        String message = (authException != null && authException.getMessage() != null) ? authException.getMessage() 
                : "Unauthorized";

        // Get the request URI to include in the response
        String path = request.getRequestURI();

        // Set custom headers in the response
        response.setHeader("ourdept-error-reason", "Authentication failed");

        // Set the HTTP status to 401 Unauthorized
        response.setStatus(HttpStatusCodes.UNAUTHORIZED);

        // Set the content type of the response to JSON
        response.setContentType("application/json;charset=UTF-8");

        // Construct the ApiResponse object with ErrorDetails
        ApiResponse<ErrorDetails> apiResponse = new ApiResponse<>(
                HttpStatus.UNAUTHORIZED.value(), 
                message, 
                new ErrorDetails(currentTimeStamp, HttpStatus.UNAUTHORIZED.getReasonPhrase(), path)
        );

        // Convert the ApiResponse object to JSON string
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);

        // Write the JSON response to the HttpServletResponse output stream
        response.getWriter().write(jsonResponse);
    }
}