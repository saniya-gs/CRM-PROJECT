package com.ourdept.crm_software.company.security.filters;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * A custom filter that logs the authorities of an authenticated user after successful authentication.
 * This filter is typically used after the user has been authenticated to log their roles or authorities.
 */
@Slf4j
public class AuthoritiesLoggingAfterFilter implements Filter {

    /**
     * Filters the request and response, logging the authenticated user's authorities if authentication is present.
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
        
        // Get the current authentication information from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // If the user is authenticated, log their username and authorities
        if (null != authentication) {
            log.info("User " + authentication.getName() + " is successfully authenticated and "
                    + "has the authorities " + authentication.getAuthorities().toString());
        }

        // Continue the filter chain, passing the request and response to the next filter
        chain.doFilter(request, response);
    }
}
