
package com.ourdept.crm_software.company.security.config;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ourdept.crm_software.crm.domain.entities.core.Employee;
import com.ourdept.crm_software.crm.repository.EmployeeRepository;
import com.ourdept.crm_software.tenant_Routing_helper.TenantContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Custom implementation of Spring Security's UserDetailsService interface.
 * This class is responsible for loading user-specific data during the authentication process.
 */
@Slf4j
@Service
@RequiredArgsConstructor  // Automatically generates a constructor for all final fields.
public class TenantUserDetailsService implements UserDetailsService {

    // Injecting the CustomerRepository dependency via constructor injection
//    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    /**
     * Loads the user details based on the username (email in this case).
     *
     * @param username The username (email) provided during login.
     * @return UserDetails object containing user information, including roles and permissions.
     * @throws UsernameNotFoundException if the user is not found in the database.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	log.info("fetching the user details from Teanat DB");
        // Retrieve the customer from the database using the email (username)
        Optional<Employee> employee = employeeRepository.findByCompanyEmail(username);
        
        if(employee.isEmpty()) {
        	
        	throw new UsernameNotFoundException("User details not found for the user: " + username+ " from the tenant "+ TenantContext.getCurrentTenant());
        }
        
Employee user=employee.get();
        
        // Convert the customer's roles/authorities into GrantedAuthority objects
        List<GrantedAuthority> authorities = user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());
        
    	log.info("fetched the user details from Teanat DB"+ TenantContext.getCurrentTenant()+ " email: "+user.getEmail()+" password: "+ user.getPassword()+"");


        // Return a Spring Security User object containing the customer's email, password, and authorities
        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
