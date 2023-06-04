package by.anabios13.authorizationService.config;

import by.anabios13.authorizationService.filters.JWTFilter;
import by.anabios13.authorizationService.repository.UserRepository;
import by.anabios13.authorizationService.security.JWTUtil;
import by.anabios13.authorizationService.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JWTFilter jwtFilter;
    private final JWTUtil jwtUtil;



    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, UserRepository userRepository, JWTFilter jwtFilter, JWTUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.jwtFilter = jwtFilter;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/client").hasRole("Client")
                .antMatchers("/insurance_agency").hasRole("Insurance agency")
                .antMatchers("/Estimator").hasRole("Estimator")
                .antMatchers("/auth/login", "/auth/registration", "/error", "/api/hello", "/show").permitAll()
                .anyRequest().hasAnyRole("Client", "Insurance agency", "Estimator")
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager()
            throws Exception {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
