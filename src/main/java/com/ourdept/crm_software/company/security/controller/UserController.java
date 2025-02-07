
package com.ourdept.crm_software.company.security.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ourdept.crm_software.company.security.Service.UserContextService;
import com.ourdept.crm_software.company.security.constants.ApplicationConstants;
import com.ourdept.crm_software.company.security.dto.LoginResponseDTO;
import com.ourdept.crm_software.company.security.dto.ModuleAccessDTO;
import com.ourdept.crm_software.crm.domain.entities.core.Employee;
import com.ourdept.crm_software.crm.domain.entities.purchaseplan.ModuleFeatures;
import com.ourdept.crm_software.crm.domain.entities.purchaseplan.TenantPurchaseDetails;
import com.ourdept.crm_software.crm.repository.EmployeeRepository;
import com.ourdept.crm_software.crm.repository.plans.TenantPurchaseDetailsRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * Controller class to handle user-related operations such as registration and
 * login.
 */
@RestController
@RequiredArgsConstructor // Automatically generates a constructor for all final fields.
public class UserController {

	// Injecting dependencies via constructor injection
	private final EmployeeRepository employeeRepository;;

	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final Environment env;

	private final UserContextService contextService;
	private final TenantPurchaseDetailsRepository purchaseDetailsRepository;

	
	/**
	 * Retrieves the user's details after they have successfully logged in.
	 *
	 * @param authentication The authentication object containing the user's
	 *                       credentials.
	 * @return The customer object corresponding to the logged-in user, or null if
	 *         not found.
	 */
	@PostMapping("/employeeLogin")
	public ResponseEntity<LoginResponseDTO> getUserDetailsAfterLogin(Authentication authentication, HttpServletResponse response) {
		// Retrieve the customer details from the database using the authenticated
		String jwt = "";
		Employee employee = null;
		List<String> authorities = null;
		String role = null;
		String fullName = null;
		String profileImageName = null;
		String profileImageType = null;
		byte[] profileImage = null;
		employee = contextService.getCurrentUser();
		long companyId=0L;
		
//		employee = optionalemployee.get();

		fullName = employee.getFirstName() + " " + employee.getLastName();
		role = (employee.getRoles() != null) ? employee.getRoles().getRoleName() : null;
		profileImageName = (employee.getProfileImageName() != null) ? employee.getProfileImageName() : null;
		profileImageType = (employee.getProfileImageType() != null) ? employee.getProfileImageType() : null;
		profileImage = (employee.getProfileImage() != null) ? employee.getProfileImage() : null;		
		
		companyId = employee.getCompany()!= null ? employee.getCompany().getCompanyId(): null;
		String logoName = employee.getCompany()!= null ? employee.getCompany().getLogoName(): null ;
		String logoType = employee.getCompany()!= null ? employee.getCompany().getLogoType(): null ;
		byte[] logo = employee.getCompany()!= null ? employee.getCompany().getLogo(): null;

		authorities = employee.getAuthorities().stream().map(authority -> authority.getName())
				.collect(Collectors.toList());
		
		jwt=response.getHeader(ApplicationConstants.JWT_HEADER);
		
		List<ModuleAccessDTO> accessDTO= fetchActivePurchaseDetailsWithModules(companyId);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), fullName, authorities, role,
						profileImageName, profileImageType, profileImage, logoName, logoType, logo, accessDTO ));
	}
	
	 private List<ModuleAccessDTO> fetchActivePurchaseDetailsWithModules(long companyId) {
	        LocalDate currentDate = LocalDate.now();
	        List<TenantPurchaseDetails> activePurchaseDetails =
	                purchaseDetailsRepository.findActivePurchaseDetails(currentDate, companyId);

	        return activePurchaseDetails.stream()
	                .flatMap(purchaseDetail -> purchaseDetail.getModulePlans().stream())
	                .map(modulePlan -> new ModuleAccessDTO(
	                        modulePlan.getModuleAccessID(),
	                        modulePlan.getModuleFeatures().stream()
	                                .map(ModuleFeatures::getFeatureAccessID)
	                                .collect(Collectors.toList())
	                ))
	                .collect(Collectors.toList());
	    }
	
//	 // Endpoint to refresh access token using refresh token
//    @PostMapping("/refresh-token")
//    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
//        String refreshToken = request.getHeader(ApplicationConstants.JWT_REFRESH_HEADER);
//        if (refreshToken != null) {
//            try {
//                String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
//                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//
//                // Validate refresh token
//                Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(refreshToken).getBody();
//                String username = claims.getSubject();
//
//                // Generate new access token
//                String newAccessToken = Jwts.builder()
//                        .setSubject(username)
//                        .setIssuedAt(new Date())
//                        .setExpiration(new Date(System.currentTimeMillis() + 300000)) // New short-lived access token
//                        .signWith(secretKey)
//                        .compact();
//
//                return ResponseEntity.ok().header(ApplicationConstants.JWT_HEADER, newAccessToken).build();
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Refresh token is missing");
//        }
//    }
//	
	 // Endpoint to invalidate tokens and clear session
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext(); // Clear authentication context
        
        return ResponseEntity.ok("Logged out successfully");
    }

	

	/**
	 * Handles API-based login and returns a JWT token upon successful
	 * authentication.
	 *
	 * @param loginRequest The login request object containing the username and
	 *                     password.
	 * @return ResponseEntity containing the JWT token and status information.
	 */
	/*
	 * @PostMapping("/employeeLogin")
	 * 
	 * public ResponseEntity<LoginResponseDTO> apiLogin(@RequestBody LoginRequestDTO
	 * loginRequest) { String jwt = ""; Employee employee = null; List<String>
	 * authorities = null; String role = null; String fullName = null; String
	 * profileImageName = null; String profileImageType = null; byte[] profileImage
	 * = null;
	 * 
	 * // Create an unauthenticated token using the provided username and password
	 * Authentication authentication =
	 * UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(),
	 * loginRequest.password());
	 * 
	 * // Authenticate the user using the authentication manager Authentication
	 * authenticationResponse = authenticationManager.authenticate(authentication);
	 * 
	 * // If authentication is successful, generate a JWT token if
	 * (authenticationResponse != null && authenticationResponse.isAuthenticated())
	 * { if (env != null) { // Retrieve the JWT secret key from the environment
	 * properties String secret =
	 * env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
	 * ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
	 * 
	 * // Create a SecretKey for signing the JWT token SecretKey secretKey =
	 * Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	 * 
	 * // Build the JWT token with claims and sign it with the secret key jwt =
	 * Jwts.builder().issuer("Our Dept").subject("JWT Token") .claim("username",
	 * authenticationResponse.getName()) .claim("authorities",
	 * authenticationResponse.getAuthorities().stream().map(GrantedAuthority::
	 * getAuthority) .collect(Collectors.joining(","))) .issuedAt(new
	 * java.util.Date()) .expiration(new java.util.Date((new
	 * java.util.Date()).getTime() + 30000000)).signWith(secretKey) .compact();
	 * Optional<Employee> optionalemployee =
	 * employeeRepository.findByEmail(authenticationResponse.getName()); if
	 * (optionalemployee.isPresent()) { employee = optionalemployee.get();
	 * 
	 * fullName = employee.getFirstName() + " " + employee.getLastName(); role =
	 * (employee.getRoles() != null) ? employee.getRoles().getRoleName() : null;
	 * profileImageName = (employee.getProfileImageName() != null) ?
	 * employee.getProfileImageName() : null; profileImageType =
	 * (employee.getProfileImageType() != null) ? employee.getProfileImageType() :
	 * null; profileImage = (employee.getProfileImage() != null) ?
	 * employee.getProfileImage() : null;
	 * 
	 * authorities = employee.getAuthorities().stream().map(authority ->
	 * authority.getName()) .collect(Collectors.toList()); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * // Return the JWT token in the response header along with the status and
	 * message return
	 * ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER,
	 * jwt) .body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt,
	 * fullName, authorities, role, profileImageName, profileImageType,
	 * profileImage)); }
	 */
	

	/**
	 * Registers a new user in the system.
	 *
	 * @param customer The customer object containing user details.
	 * @return ResponseEntity with a success or error message.
	 */
//	@PostMapping("/register")
//	public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
//		try {
//			// Encode the user's password before saving it in the database
//			String hashPwd = passwordEncoder.encode(customer.getPwd());
//			customer.setPwd(hashPwd);
//			customer.setCreateDt(new Date(System.currentTimeMillis()));
//
//			// Save the customer in the database
//			Customer savedCustomer = customerRepository.save(customer);
//
//			// Return a response based on whether the customer was successfully saved
//			if (savedCustomer.getId() > 0) {
//				return ResponseEntity.status(HttpStatus.CREATED).body("Given user details are successfully registered");
//			} else {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
//			}
//		} catch (Exception ex) {
//			// Handle any exceptions that occur during registration
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("An exception occurred: " + ex.getMessage());
//		}
//	}
	

}
