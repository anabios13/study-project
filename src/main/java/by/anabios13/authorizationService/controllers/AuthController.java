package by.anabios13.authorizationService.controllers;

import by.anabios13.authorizationService.dto.AuthenticationDTO;
import by.anabios13.authorizationService.dto.UserDTO;
import by.anabios13.authorizationService.services.AuthorizationService;
import by.anabios13.authorizationService.services.RegistrationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;

    private final AuthorizationService authorizationService;


    public AuthController(RegistrationService registrationService, AuthorizationService authorizationService) {
        this.registrationService = registrationService;
        this.authorizationService = authorizationService;

    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> performRegistration(@RequestBody UserDTO userDTO) {
        return registrationService.performRegistration(userDTO);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> performLogin(@RequestBody AuthenticationDTO authenticationDTO) throws BadCredentialsException {
         return authorizationService.performLogin(authenticationDTO);
    }
}
