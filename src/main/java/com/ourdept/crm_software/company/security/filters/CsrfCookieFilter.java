package com.ourdept.crm_software.company.security.filters;

import java.io.IOException;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A custom filter that ensures the CSRF token is included in the response as a cookie.
 * This filter is executed once per request, ensuring that the CSRF token is always rendered.
 */
public class CsrfCookieFilter extends OncePerRequestFilter {

    /**
     * Filters the request and response to ensure the CSRF token is included as a cookie.
     * This method is called once per request and ensures that the CSRF token is fetched and 
     * added to the response.
     *
     * @param request  The HttpServletRequest being processed
     * @param response The HttpServletResponse associated with the request
     * @param filterChain Provides access to the next filter in the chain
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error occurs during the processing of the request
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Retrieve the CSRF token from the request
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        // Force the CSRF token to be included in the response by accessing the token value
        csrfToken.getToken();

        // Continue the filter chain, passing the request and response to the next filter
        filterChain.doFilter(request, response);
    }
}
