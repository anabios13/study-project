package by.anabios13.authorizationService.controllers;

import by.anabios13.authorizationService.dto.AuthenticationDTO;
import by.anabios13.authorizationService.dto.UserDTO;
import by.anabios13.authorizationService.security.JWTUtil;
import by.anabios13.authorizationService.services.AuthorizationService;
import by.anabios13.authorizationService.services.RegistrationService;
import by.anabios13.authorizationService.services.UserDetailsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import java.util.Collections;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;

    private final AuthorizationService authorizationService;
    private  AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;



    public AuthController(RegistrationService registrationService, AuthorizationService authorizationService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JWTUtil jwtUtil) {
        this.registrationService = registrationService;
        this.authorizationService = authorizationService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> performRegistration(@RequestBody UserDTO userDTO) {
        return registrationService.performRegistration(userDTO);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> performLogin(@RequestBody AuthenticationDTO authenticationDTO) throws AuthException {

//        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getLogin());
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(),authenticationDTO.getPassword());
//        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtUtil.generateToken(authenticationDTO.getLogin());
//        return ResponseEntity.ok(Collections.singletonMap("token", token));
         return authorizationService.performLogin(authenticationDTO);
    }
}
