package com.ourdept.crm_software.crm.auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        return Optional.of("Devlopers");
//    }
//    
    
    

    @Override
    public Optional<String> getCurrentAuditor() {
        // Fetch the current authenticated user from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            // If the principal is an instance of UserDetails, return the username
            return Optional.of(((UserDetails) principal).getUsername());
        }

        // Return the principal's string representation (it might be a simple username)
        return Optional.of(principal.toString());
    }
	
}