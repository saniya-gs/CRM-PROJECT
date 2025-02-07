package com.ourdept.crm_software.company.security.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ourdept.crm_software.ExceptionHandler.UnauthorizedException;
import com.ourdept.crm_software.company.security.constants.ApplicationConstants;
import com.ourdept.crm_software.crm.repository.EmployeeRepository;
import com.ourdept.crm_software.tenant_Routing_helper.TenantContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTTokenValidatorFilter extends OncePerRequestFilter {
	
	

    private final EmployeeRepository employeeRepository;

    // Inject the repository via the constructor
    public JWTTokenValidatorFilter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
	
	/**
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.info(getFilterName() + " got triggered(JWT validator");
		String jwt = request.getHeader(ApplicationConstants.JWT_HEADER);
		if (null != jwt) {
			try {
				Environment env = getEnvironment();
				if (null != env) {
					String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
							ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
					SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
					if (null != secretKey) {
						Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt).getPayload();
						String username = String.valueOf(claims.get("username"));
						String authorities = String.valueOf(claims.get("authorities"));
						String tenantID = String.valueOf(claims.get("tenantID",String.class));					
			            TenantContext.setCurrentTenant(tenantID);
			            
//			            if(tenantID==null) {
//		                    throw new UnauthorizedException("unknown Tenant");
//
//			            }
//						
						   // Verify username and tenantId match a record in the database
		                if (!employeeRepository.existsByCompanyEmail(username)) {
		                    throw new UnauthorizedException("Invalid tenant access.");
		                }
						
						Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
								AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
						SecurityContextHolder.getContext().setAuthentication(authentication);
						log.info(getFilterName() + " got validated(JWT validator");

					}
				}

			} catch (Exception exception) {
				throw new UnauthorizedException("token expired, please login again: " + exception.getMessage());
			}
		}
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().equals("/employeeLogin");
	}

}