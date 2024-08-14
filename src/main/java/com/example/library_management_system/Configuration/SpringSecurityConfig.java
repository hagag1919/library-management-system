package com.example.library_management_system.Configuration;

import com.example.library_management_system.Components.JwtAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers(HttpMethod.POST, "/api/books/update").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.POST, "/api/books/delete").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.POST, "/api/books/getAll").hasAnyRole("ADMIN", "USER");
                    authorize.requestMatchers(HttpMethod.POST, "/api/books/get").hasAnyRole("ADMIN", "USER");
                    authorize.requestMatchers(HttpMethod.POST, "/api/borrowing-record/borrow-book").hasRole("USER");
                    authorize.requestMatchers(HttpMethod.POST, "/api/borrowing-record/return-book").hasRole("USER");
                    authorize.requestMatchers(HttpMethod.POST, "/api/patrons/add").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.POST, "/api/patrons/update").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.POST, "/api/patrons/delete").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.POST, "/api/patrons/get").hasAnyRole("ADMIN", "USER");
                    authorize.requestMatchers(HttpMethod.POST, "/api/patrons/getall").hasAnyRole("ADMIN", "USER");
                    authorize.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/api/auth/register").hasRole("USER");
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails ramesh = User.builder()
                .username("hagag")
                .password(passwordEncoder().encode("hagag1919pass"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin123456pass"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ramesh, admin);
    }
}