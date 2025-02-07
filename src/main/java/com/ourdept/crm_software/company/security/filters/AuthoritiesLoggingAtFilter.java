package com.ourdept.crm_software.company.security.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * A custom filter that logs a message indicating that authentication validation is in progress.
 * This filter is typically used to log information before the authentication process takes place.
 */
@Slf4j
public class AuthoritiesLoggingAtFilter implements Filter {

    /**
     * Filters the request and response, logging a message before proceeding with the filter chain.
     *
     * @param request  The ServletRequest being processed
     * @param response The ServletResponse associated with the request
     * @param chain    Provides access to the next filter in the chain, allowing the request and response to proceed
     * @throws IOException      if an input or output error occurs during the processing of the request
     * @throws ServletException if the request could not be handled
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Log a message indicating that the authentication validation process is starting
        log.info("Authentication Validation is in progress");

        // Continue the filter chain, passing the request and response to the next filter
        chain.doFilter(request, response);
    }
}
