package com.ourdept.crm_software.company.security.config;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Custom authentication provider for handling username and password authentication.
 * This class implements Spring Security's AuthenticationProvider interface and defines
 * custom logic for authenticating users based on username and password.
 */
@Slf4j
@Component
@Profile("prod")  // This configuration is active in all profiles except "prod" (production).
@RequiredArgsConstructor  // Automatically generates a constructor for all final fields.
public class ProdTenantUserPwdAuthenticationProvider implements AuthenticationProvider {

    // Injecting dependencies via constructor injection
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Authenticates the user based on the provided username and password.
     *
     * @param authentication The authentication request object containing credentials.
     * @return Authentication object if authentication is successful.
     * @throws AuthenticationException if authentication fails (e.g., bad credentials).
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Extracting the username and password from the authentication request
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        // Loading user details from the database or other persistence mechanism
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Verifying the provided password against the stored password
        if (!passwordEncoder.matches(pwd, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // If authentication is successful, return a fully authenticated object, 
        // including the user's authorities (roles/permissions)
        return new UsernamePasswordAuthenticationToken(username, pwd, userDetails.getAuthorities());
    }

    /**
     * Indicates whether this AuthenticationProvider supports the specified authentication type.
     *
     * @param authentication The type of authentication to be supported.
     * @return true if the authentication type is supported, false otherwise.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // This provider supports UsernamePasswordAuthenticationToken type of authentication.
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}