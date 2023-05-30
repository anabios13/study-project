package by.anabios13.authorizationService.controllers;

import by.anabios13.authorizationService.dto.AuthenticationDTO;
import by.anabios13.authorizationService.dto.UserDTO;
import by.anabios13.authorizationService.models.User;
import by.anabios13.authorizationService.models.Role;
import by.anabios13.authorizationService.pojo.ResponseWithMessage;
import by.anabios13.authorizationService.security.JWTUtil;
import by.anabios13.authorizationService.services.AuthorizationService;
import by.anabios13.authorizationService.services.RegistrationService;
import by.anabios13.authorizationService.services.RoleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JWTUtil jwtUtil;
    private final RegistrationService registrationService;
    private final RoleService roleService;
    private final AuthorizationService authorizationService;



    public AuthController(JWTUtil jwtUtil, RegistrationService registrationService, RoleService roleService, AuthorizationService authorizationService) {
        this.jwtUtil = jwtUtil;
        this.registrationService = registrationService;
        this.roleService = roleService;
        this.authorizationService = authorizationService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseWithMessage performRegistration(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getFirstName());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        Role role = roleService.searchById(userDTO.getRoleId());
        user.setRole(role);
        registrationService.register(user, role);
        String token = jwtUtil.generateToken(user.getLogin());
        return new ResponseWithMessage(token);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseWithMessage performLogin(@RequestBody AuthenticationDTO authenticationDTO) throws AuthException {
        return authorizationService.performLogin(authenticationDTO);
    }
}
