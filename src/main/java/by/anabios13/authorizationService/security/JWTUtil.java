package by.anabios13.authorizationService.security;

import by.anabios13.authorizationService.services.PersonDetailsService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {
    private final PersonDetailsService personDetailsService;
    @Value("${jwt_secret}")
    private String secretWord;

    @Value("${jwt_expired}")
    private long timeOfExpirationInMinutes;

    public JWTUtil(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    public String generateToken(String login){
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(timeOfExpirationInMinutes).toInstant());
        return JWT.create().withSubject("Person details").
                withClaim("login",login).
                withIssuer("authService").
                withIssuedAt(new Date()).
                withExpiresAt(expirationDate).
                sign(Algorithm.HMAC256(secretWord));
    }
    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretWord)).
                    withSubject("Person details").
                    withIssuer("authService").
                    build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("login").asString();
        }catch (TokenExpiredException | IllegalArgumentException e){
            throw new TokenExpiredException("JWT token is expired", Instant.now());
        }
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken !=null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = this.personDetailsService.loadUserByUsername(validateTokenAndRetrieveClaim(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

}
