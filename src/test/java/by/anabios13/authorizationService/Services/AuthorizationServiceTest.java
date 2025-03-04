package by.anabios13.authorizationService.Services;

import by.anabios13.authorizationService.dto.AuthResponseDTO;
import by.anabios13.authorizationService.dto.AuthenticationDTO;
import by.anabios13.authorizationService.models.User;
import by.anabios13.authorizationService.repository.UserRepository;
import by.anabios13.authorizationService.security.JWTUtil;
import by.anabios13.authorizationService.services.AuthorizationService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorizationServiceTest {
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JWTUtil jwtUtil;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthorizationService authorizationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void performLoginValidCredentialsReturnsSuccessfulResponse() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Smith");
        AuthenticationDTO authenticationDTO = new AuthenticationDTO("username", "password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getPassword());
        Authentication authentication = mock(Authentication.class);
        Collection grantedAuthorities = Lists.newArrayList(new SimpleGrantedAuthority("ROLE_Client"));
        when(userRepository.findByLogin("username")).thenReturn(Optional.of(user));
        when(authenticationManager.authenticate(authenticationToken)).thenReturn(authentication);
        when(authentication.getAuthorities()).thenReturn(grantedAuthorities);
        when(jwtUtil.generateToken(authenticationDTO.getLogin())).thenReturn("token");

        ResponseEntity<AuthResponseDTO> response = authorizationService.performLogin(authenticationDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("[ROLE_Client]", response.getBody().getRole());
        assertEquals("John", response.getBody().getFirstname());
        assertEquals("Smith", response.getBody().getLastName());

        verify(authenticationManager).authenticate(authenticationToken);
        verify(jwtUtil).generateToken(authenticationDTO.getLogin());
        verify(userRepository).findByLogin("username");
    }

    @Test
    void performLoginEmptyPasswordThrowsBadCredentialsException() {
        AuthenticationDTO authenticationDTO = new AuthenticationDTO("username", "");
        ResponseEntity<AuthResponseDTO> response = authorizationService.performLogin(authenticationDTO);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

    }

    @Test
    void performLoginInvalidCredentialsReturnsUnauthorizedResponse() {

        AuthenticationDTO authenticationDTO = new AuthenticationDTO("username", "password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getPassword());
        when(authenticationManager.authenticate(authenticationToken)).thenThrow(new BadCredentialsException("Invalid credentials"));

        ResponseEntity<AuthResponseDTO> response = authorizationService.performLogin(authenticationDTO);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        verify(authenticationManager).authenticate(authenticationToken);
    }

    void tearDown(){
        verifyNoMoreInteractions(authenticationManager,jwtUtil);
    }
}
