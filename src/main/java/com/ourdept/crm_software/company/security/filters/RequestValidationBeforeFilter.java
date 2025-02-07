package com.ourdept.crm_software.company.security.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Filter that validates the "Basic" authentication token in the request header.
 * This filter performs a check on the basic authentication token, particularly ensuring that
 * it does not contain a specific keyword ("test") in the email part of the token.
 */
@Slf4j
public class RequestValidationBeforeFilter implements Filter {

    /**
     * Processes the request to validate the Basic authentication token.
     * If the token contains a specific keyword ("test") in the email part,
     * the response is set to a bad request status. Otherwise, the request
     * is passed along the filter chain.
     *
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
     *                 to for further processing
     * @throws IOException      if an input or output error occurs during processing
     * @throws ServletException if the request could not be handled
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	
    	log.info("RequestValidation filter triggered");
        
        // Cast ServletRequest and ServletResponse to HttpServletRequest and HttpServletResponse
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        // Retrieve the Authorization header from the request
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        
        if (header != null) {
            header = header.trim();
            
            // Check if the header starts with "Basic "
            if (StringUtils.startsWithIgnoreCase(header, "Basic ")) {
                // Extract the Base64 encoded part of the header
                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decoded;
                
                try {
                    // Decode the Base64 token
                    decoded = Base64.getDecoder().decode(base64Token);
                    String token = new String(decoded, StandardCharsets.UTF_8); // "username:password"
                    
                    // Find the delimiter (colon) to split username and password
                    int delim = token.indexOf(":");
                    if (delim == -1) {
                        // If no delimiter is found, throw a BadCredentialsException
                        throw new BadCredentialsException("Invalid basic authentication token");
                    }
                    
                    // Extract the email (username) from the token
                    String email = token.substring(0, delim);
                    
                    // Check if the email contains the keyword "test"
                    if (email.toLowerCase().contains("test")) {
                        // If it does, set the response status to BAD_REQUEST and return
                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                        return;
                    }
                } catch (IllegalArgumentException exception) {
                    // If decoding fails, throw a BadCredentialsException
                    throw new BadCredentialsException("Failed to decode basic authentication token");
                }
            }
        }
    	log.info("RequestValidation filter completed");

        // Continue the filter chain if no issues were found
        chain.doFilter(request, response);
    }
}
