package by.anabios13.authorizationService.filters;

import by.anabios13.authorizationService.security.JWTUtil;
import by.anabios13.authorizationService.services.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public JWTFilter(JWTUtil jwtUtil,
                     PasswordEncoder passwordEncoder,
                     AuthenticationManager authenticationManager,
                     UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication;
        String token = jwtUtil.resolveToken(request);
        if (token != null && jwtUtil.validateTokenAndRetrieveClaimLogin(token) != null) {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtil.validateTokenAndRetrieveClaimLogin(token));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities());
                authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
