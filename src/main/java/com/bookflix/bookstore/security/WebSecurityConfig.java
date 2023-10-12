package com.bookflix.bookstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bookflix.bookstore.security.jwt.AuthTokenFilter;

import lombok.RequiredArgsConstructor;
// import static com.bookflix.bookstore.utils.Role.ADMIN;
// import static com.bookflix.bookstore.utils.Permission.ADMIN_READ;
// import static com.bookflix.bookstore.utils.Permission.ADMIN_UPDATE;
// import static com.bookflix.bookstore.utils.Permission.ADMIN_CREATE;
// import static com.bookflix.bookstore.utils.Permission.ADMIN_DELETE;

// import static org.springframework.http.HttpMethod.GET;
// import static org.springframework.http.HttpMethod.POST;
// import static org.springframework.http.HttpMethod.PUT;
// import static org.springframework.http.HttpMethod.DELETE;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {
    
    private final AuthTokenFilter authTokenFilter;
    private final AuthenticationProvider authenticationProvider;
    private final String[] WHITE_LIST_URL = {"/api/auth/**", "/allbooks/**", "/allcategories", "/allsubcategories", "/uploads/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(WHITE_LIST_URL).permitAll()
                                .anyRequest().authenticated())
                // .authorizeHttpRequests(auth -> 
                //         auth.requestMatchers("/api/admin/**").hasRole(ADMIN.name())
                //             .requestMatchers(GET, "/api/admin/**").hasAuthority(ADMIN_READ.name())
                //             .requestMatchers(POST, "/api/admin/**").hasAuthority(ADMIN_CREATE.name())
                //             .requestMatchers(PUT, "/api/admin/**").hasAuthority(ADMIN_UPDATE.name())
                //             .requestMatchers(DELETE, "/api/admin/**").hasAuthority(ADMIN_DELETE.name())
                // )   
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
    }
}
