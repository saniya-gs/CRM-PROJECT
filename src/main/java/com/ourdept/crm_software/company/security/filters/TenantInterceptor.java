package com.ourdept.crm_software.company.security.filters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ourdept.crm_software.tenant_Routing_helper.TenantContext;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@WebFilter("/*")  // You can specify the URL pattern here if necessary
public class TenantInterceptor implements Filter {

	

	  
    
    private static final Logger logger = LoggerFactory.getLogger(TenantInterceptor.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialization needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        
        // Cast to HttpServletRequest and HttpServletResponse
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        try {
            logger.info("Tenant context filter, intercepts and gets the Tenant ID from the request");
            
            // Extract Tenant ID from the request header
            String tenantID = httpRequest.getHeader("X-TenantID");  // Or get from subdomain
            if (tenantID == null) {
                tenantID = "defaultTenant";  // Fallback to a default tenant or handle appropriately
//                throw new ResourceNotFoundException("Tenant not found for the tenant: ");
                logger.info("Setting the tenant context for Tenant ID as: " + tenantID + " as tenantId was null");

            }

            // Set the Tenant ID in the TenantContext
            logger.info("Setting the tenant context for Tenant ID: " + tenantID);
            TenantContext.setCurrentTenant(tenantID);
            
//            ensureTenantDataSourceResolved(tenantID);


            // Proceed with the filter chain
            filterChain.doFilter(request, response);
        } finally {
            // Cleanup - Clear the Tenant Context after the request is processed
            TenantContext.clear();
            logger.info("Tenant context cleared after request processing.");
        }
    }

    @Override
    public void destroy() {
        // No cleanup needed
    }
    
    /**
     * Trigger early resolution of the tenant data source by performing a dummy query.
     */
//    private void ensureTenantDataSourceResolved(String tenantID) {
//        try {
//            logger.info("Triggering early tenant data source resolution.usimg tenant ID: " + tenantID);
//            employeeRepository.findById(0);
//            // Dummy operation to ensure data source is resolved
//        } catch (Exception e) {
//            logger.error("Failed to trigger early tenant data source resolution.", e);
//        }
//    }
}


//
//@Component
//public class TenantInterceptor implements HandlerInterceptor {
//	
//	private static final Logger logger = LoggerFactory.getLogger(TenantInterceptor.class);
//
//	
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//    	
//    	logger.info("tenant interceptor, intercepts and get the Tenant ID from the request ");
//    	
//        String tenantID = request.getHeader("X-TenantID"); // Or get from subdomain
//        if (tenantID == null) {
//            tenantID = "defaultTenant"; // Fallback to a default tenant or handle appropriately
//            
//            throw new ResourceNotFoundException("tenant not found for the tenant: " );
////            logger.info("sets the default tenant DB ");
//        }
//        
//        logger.info("sets the  tenant: "+tenantID+" to the tenant context");
//        TenantContext.setCurrentTenant(tenantID);
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        TenantContext.clear();
//    }
//}
