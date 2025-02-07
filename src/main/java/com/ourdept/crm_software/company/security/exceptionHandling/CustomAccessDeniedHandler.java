package com.ourdept.crm_software.company.security.exceptionHandling;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ourdept.crm_software.crm.utils.ApiResponse;
import com.ourdept.crm_software.crm.utils.ErrorDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom implementation of AccessDeniedHandler to handle cases where a user is denied access to a resource.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Handles an access denied failure, constructing and sending a custom ApiResponse.
     *
     * @param request                the HttpServletRequest being processed
     * @param response               the HttpServletResponse to send the response to
     * @param accessDeniedException  the exception that caused the access to be denied
     * @throws IOException      if an input or output error occurs
     * @throws ServletException if the request could not be handled
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        // Get the current timestamp to include in the response
        LocalDateTime currentTimeStamp = LocalDateTime.now();

        // Default error message for authorization failure
        String message = "Authorization failed";

        // Check if the exception is related to an invalid or missing CSRF token
        if (request.getAttribute("org.springframework.security.web.csrf.CsrfException") != null ||
            accessDeniedException instanceof InvalidCsrfTokenException ||
            accessDeniedException instanceof MissingCsrfTokenException) {
            
            // Specific message for CSRF token failure
            message = "Invalid or missing CSRF token";
        } else if (accessDeniedException.getMessage() != null) {
            // Use the exception message for other access denied cases
            message = accessDeniedException.getMessage();
        }

        // Get the request URI to include in the response
        String path = request.getRequestURI();

        // Set custom headers in the response
        response.setHeader("Our-Dept-HRM-Software-denied-reason", message);

        // Set the HTTP status to 403 Forbidden
        response.setStatus(HttpStatus.FORBIDDEN.value());

        // Set the content type of the response to JSON
        response.setContentType("application/json;charset=UTF-8");

        // Construct the ApiResponse object with ErrorDetails
        ApiResponse<ErrorDetails> apiResponse = new ApiResponse<>(
                HttpStatus.FORBIDDEN.value(), 
                message, 
                new ErrorDetails(currentTimeStamp, HttpStatus.FORBIDDEN.getReasonPhrase(), path)
        );

        // Convert the ApiResponse object to JSON string
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);

        // Write the JSON response to the HttpServletResponse output stream
        response.getWriter().write(jsonResponse);
    }
}
