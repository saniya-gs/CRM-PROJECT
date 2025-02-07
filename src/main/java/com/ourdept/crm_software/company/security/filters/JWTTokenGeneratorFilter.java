package com.ourdept.crm_software.company.security.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ourdept.crm_software.ExceptionHandler.UnauthorizedException;
import com.ourdept.crm_software.company.security.constants.ApplicationConstants;
import com.ourdept.crm_software.tenant_Routing_helper.TenantContext;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTTokenGeneratorFilter extends OncePerRequestFilter {
	

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	log.info("jwt token generator triggered");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Get the Tenant ID from the request (assuming TenantContext is already set by the TenantInterceptor)
        String tenantID = TenantContext.getCurrentTenant();
        if (tenantID == null || tenantID.isEmpty()) {
            throw new UnauthorizedException("Tenant ID is not provided or invalid.");
        }
        
        if (null != authentication) {
            Environment env = getEnvironment();
            if (null != env) {
                String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                // Generate access token
                String accessToken = Jwts.builder()
                        .issuer("Our Dept")
                        .subject("JWT Token")
                        .claim("username", authentication.getName())
                        .claim("tenantID", tenantID)
                        .claim("authorities", authentication.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.joining(",")))
                        .issuedAt(new Date())
                        .expiration(new Date((new Date()).getTime() + 30000000)) // Shorter expiration for access token
                        .signWith(secretKey)
                        .compact();
                response.setHeader(ApplicationConstants.JWT_HEADER, accessToken);

                // Generate refresh token (longer expiration)
                String refreshToken = Jwts.builder()
                        .issuer("Our Dept")
                        .subject("Refresh Token")
                        .claim("username", authentication.getName())
                        .issuedAt(new Date())
                        .expiration(new Date((new Date()).getTime() + 86400000)) // 1-day expiration for refresh token
                        .signWith(secretKey)
                        .compact();

                // Set tokens in response headers
                response.setHeader(ApplicationConstants.JWT_REFRESH_HEADER, refreshToken);
            	log.info("jwt token generator set the jwt token");

            }
        } else {
            throw new UnauthorizedException("User is unauthorized");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/employeeLogin");
    }
}
