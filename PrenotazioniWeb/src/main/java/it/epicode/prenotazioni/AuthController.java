package it.epicode.prenotazioni;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.prenotazioni.model.Role;
import it.epicode.prenotazioni.model.RoleType;
import it.epicode.prenotazioni.model.User;
import it.epicode.prenotazioni.repository.RoleRepository;
import it.epicode.prenotazioni.repository.UserDetailsImpl;
import it.epicode.prenotazioni.repository.UserRepository;
import it.epicode.prenotazioni.security.JwtUtils;
import it.epicode.prenotazioni.security.LoginRequest;
import it.epicode.prenotazioni.security.LoginResponse;
import it.epicode.prenotazioni.security.WebSecurityConfig;



@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	WebSecurityConfig security;
	
	
	@PostMapping("/loginpage")
	public ResponseEntity<?> authenticateUser(HttpServletRequest request) {
		LoginRequest loginRequest=new LoginRequest(request.getParameter("username"),request.getParameter("password"));
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		
		authentication.getAuthorities();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles, userDetails.getExpirationTime()));
	}
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser( LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		authentication.getAuthorities();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(
				new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles, userDetails.getExpirationTime()));
	}
	
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        // Verifica l'esistenza di Username e Email già registrate
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new SignupResponse("Errore: Username già in uso!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new SignupResponse("Errore: Email già in uso!"));
        }
        // Crea un nuovo user codificando la password
        User user = new User(null, signUpRequest.getUsername(), signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()), signUpRequest.getNome(), signUpRequest.getCognome());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
     // Verifica l'esistenza dei Role
        if (strRoles == null) {
            Role userRole = roleRepository.findByRoletype(RoleType.ROLE_USER).orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByRoletype(RoleType.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
                    roles.add(adminRole);
                    break;
                default:
                    Role userRole = roleRepository.findByRoletype(RoleType.ROLE_USER).orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
                    roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new SignupResponse("User registrato con successo!"));
    }

}

