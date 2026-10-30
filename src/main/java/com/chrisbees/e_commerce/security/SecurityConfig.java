package com.chrisbees.e_commerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

        private static final String[] PUBLIC_URLS = {
                        "/api/v1/auth/login",
                        "/api/v1/auth/register",
                        "/api/v1/auth/refresh",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/h2-console/**"
        };
        private final JwtFilter jwtFilter;

        public SecurityConfig(JwtFilter jwtFilter) {
                this.jwtFilter = jwtFilter;
        }

        @Bean
        public SecurityFilterChain filterChain(
                        final HttpSecurity http) throws Exception {

                return http
                                .csrf(AbstractHttpConfigurer::disable)

                                .authorizeHttpRequests(auth -> auth

                                                // Authentication and documentation
                                                .requestMatchers(PUBLIC_URLS).permitAll()

                                                // Anyone can browse properties
                                                .requestMatchers(
                                                                HttpMethod.GET,
                                                                "/api/v1/properties",
                                                                "/api/v1/properties/*")
                                                .permitAll()

                                                // Anyone can browse categories
                                                .requestMatchers(
                                                                HttpMethod.GET,
                                                                "/api/v1/categories",
                                                                "/api/v1/categories/**")
                                                .permitAll()

                                                // Only ADMIN can create and modify categories
                                                .requestMatchers(
                                                                HttpMethod.POST,
                                                                "/api/v1/categories")
                                                .hasRole("ADMIN")

                                                .requestMatchers(
                                                                HttpMethod.PATCH,
                                                                "/api/v1/categories/**")
                                                .hasRole("ADMIN")

                                                // AGENT and ADMIN can create properties
                                                .requestMatchers(
                                                                HttpMethod.POST,
                                                                "/api/v1/properties")
                                                .hasAnyRole("AGENT", "ADMIN")

                                                // AGENT and ADMIN can update or delete properties
                                                .requestMatchers(
                                                                HttpMethod.PATCH,
                                                                "/api/v1/properties/**")
                                                .hasAnyRole("AGENT", "ADMIN")

                                                // AGENT and ADMIN can upload property images
                                                .requestMatchers(
                                                                HttpMethod.POST,
                                                                "/api/v1/properties/*/images")
                                                .hasAnyRole("AGENT", "ADMIN")

                                                // Other requests require explicit authorization
                                                .anyRequest().denyAll())

                                .sessionManagement(sess -> sess
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                .addFilterBefore(
                                                jwtFilter,
                                                UsernamePasswordAuthenticationFilter.class)

                                .headers(headers -> headers
                                                .frameOptions(frame -> frame.sameOrigin()))

                                .build();
        }

}
